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


class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var enlaceRegistrod: TextView
    private lateinit var enlaceRegistro: TextView
    private lateinit var managerBd: ManagerBd

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

        // Set click listener for login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Check if email and password are not empty
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Call method to validate credentials
                val cursor = managerBd.obtenerDatosPorCorreo(email)
                if (cursor != null && cursor.moveToFirst()) {
                    val storedPassword = cursor.getString(cursor.getColumnIndex("contrasena"))
                    if (password == storedPassword) {
                        // Login successful, navigate to next destination
                        Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        // Navigate to the next destination after successful login
                         findNavController().navigate(R.id.nav_home)
                    } else {
                        // Password incorrect
                        Toast.makeText(requireContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Email not found
                    Toast.makeText(requireContext(), "Correo no registrado", Toast.LENGTH_SHORT).show()
                }
                cursor?.close() // Close the cursor when no longer needed
            } else {
                // Email or password field is empty
                Toast.makeText(requireContext(), "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listener for registration link
        enlaceRegistro.setOnClickListener {
            findNavController().navigate(R.id.fragment_register)
        }


       enlaceRegistrod.setOnClickListener {
            findNavController().navigate(R.id.fragment_verifyteacher)
        }


        return view
    }

}
