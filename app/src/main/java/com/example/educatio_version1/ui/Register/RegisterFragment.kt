package com.example.educatio_version1.ui.Register



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.educatio_version1.R
import com.example.educatio_version1.databinding.FragmentRegisterBinding
import com.example.educatio_version1.ui.Register.ManagerBd

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        // Configurar el listener para el evento de clic del botón de registro
        binding.botonRegistro.setOnClickListener {
            val cedula = binding.identificacion.text.toString()
            val nombre = binding.name.text.toString()
            val fechaNacimiento = binding.fechaN.text.toString()
            val ciudad = binding.ciudad.text.toString()
            val telefono = binding.telefono.text.toString()
            val correo = binding.email.text.toString()
            val contrasena = binding.contrasena.text.toString()

            // Aquí debes realizar la validación de entrada

            // Llamar al método insertarDatos() de ManagerBd para insertar el registro en la base de datos
            val managerBd = ManagerBd(requireContext()) // Usar requireContext() para obtener el contexto del fragmento
            val resultado = managerBd.insertarDatos(cedula, nombre, fechaNacimiento, ciudad, telefono, correo, contrasena)

            // Manejar el resultado según corresponda
            if (resultado != -1L) {
                Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}