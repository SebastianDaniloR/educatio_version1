package com.example.educatio_version1.ui.Login

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.educatio_version1.R
import com.example.educatio_version1.ui.Register.ManagerBd
import com.example.educatio_version1.ui.Verify_teacher.Managerbd

class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var enlaceRegistrod: TextView
    private lateinit var enlaceRegistro: TextView
    private lateinit var managerBd: ManagerBd
    private lateinit var managerBdVerifyTeacher: Managerbd

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Initialize views and ManagerBd instance
        emailEditText = view.findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = view.findViewById(R.id.editTextNumberPassword)
        loginButton = view.findViewById(R.id.bottonInicioDeSesion)
        enlaceRegistrod = view.findViewById(R.id.enlace_registrod)
        enlaceRegistro = view.findViewById(R.id.enlace_registro)
        managerBd = ManagerBd(requireContext())
        managerBdVerifyTeacher = Managerbd(requireContext())


        // Set click listener for login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Check if email and password are not empty
            if (email.isNotEmpty() && password.isNotEmpty()) {
                var isLoginSuccessful = false

                // Check in the first database (for regular users)
                val cursor = managerBd.obtenerDatosPorCorreo(email)
                if (cursor != null && cursor.moveToFirst()) {
                    val storedPassword = cursor.getString(cursor.getColumnIndex("contrasena"))
                    if (password == storedPassword) {
                        isLoginSuccessful = true
                        // Login successful, navigate to next destination
                        Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        // Navigate to the next destination after successful login
                        findNavController().navigate(R.id.nav_home)
                    }
                    cursor.close() // Close the cursor when no longer needed
                }

                // If login was not successful in the first database, check the second database (for teachers)
                if (!isLoginSuccessful) {
                    val cursorTeacher = managerBdVerifyTeacher.obtenerDatosPorCorreo(email)
                    if (cursorTeacher != null && cursorTeacher.moveToFirst()) {
                        val storedPassword = cursorTeacher.getString(cursorTeacher.getColumnIndex("contrasena"))
                        if (password == storedPassword) {
                            // inicio de sesion correcto
                            Toast.makeText(requireContext(), "Inicio de sesión exitoso (Docente)", Toast.LENGTH_SHORT).show()
                            // Hacia donde navega cuango es exitoso el inicio de sesion
                            findNavController().navigate(R.id.nav_home)
                            isLoginSuccessful = true
                        } else {
                            // Contraseña incorrecta
                            Toast.makeText(requireContext(), "Contraseña incorrecta (Docente)", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Correo no registrado
                        Toast.makeText(requireContext(), "Correo no registrado", Toast.LENGTH_SHORT).show()
                    }
                    cursorTeacher?.close() // Cerrar el cursor cuando ya no sea necesario
                }

                // Cuando el inicio de sesion sea incorrecto
                if (!isLoginSuccessful) {
                    Toast.makeText(requireContext(), "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Cuando el correo o la contraseña este vacio
                Toast.makeText(requireContext(), "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }


        // Set para llevar a fragment register por medio del link
        enlaceRegistro.setOnClickListener {
            findNavController().navigate(R.id.fragment_register)
        }


       enlaceRegistrod.setOnClickListener {
            findNavController().navigate(R.id.fragment_verifyteacher)
        }


        return view
    }


}
