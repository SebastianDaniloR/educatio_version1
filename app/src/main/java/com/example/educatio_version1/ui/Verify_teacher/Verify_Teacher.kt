package com.example.educatio_version1.ui.Verify_teacher

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.educatio_version1.databinding.FragmentVerifyteacherBinding
import com.example.educatio_version1.ui.Verify_teacher.Managerbd

class Verify_Teacher : Fragment() {

    private var _binding: FragmentVerifyteacherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyteacherBinding.inflate(inflater, container, false)
        val view = binding.root

        // Configurar el listener para el evento de clic del botón de registro
        binding.botonverificar.setOnClickListener {
            val cedula = binding.identificacion.text.toString()
            val nombre = binding.name.text.toString()
            val fechaNacimiento = binding.fechaN.text.toString()
            val ciudad = binding.ciudad.text.toString()
            val telefono = binding.telefono.text.toString()
            val correo = binding.email.text.toString()
            val contrasena = binding.contrasena.text.toString()
            val hojaVida = "" // Aquí puedes obtener el valor de la hoja de vida desde algún componente de tu UI si es necesario

            // Llamar al método insertarDatos() de ManagerBd para insertar el registro en la base de datos
            val managerBd =  Managerbd(requireContext())// Usar requireContext() para obtener el contexto del fragmento
            val resultado = managerBd.insertarDatos(cedula, nombre, fechaNacimiento, ciudad, telefono, correo, contrasena, hojaVida)

            // Manejar el resultado según corresponda
            if (resultado != -1L) {
                Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar el listener para el evento de clic del botón de adjuntar hoja de vida
        binding.botonAdjuntarHojaVida.setOnClickListener {
            seleccionarHojaVida()
        }

        return view
    }

    private fun seleccionarHojaVida() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*" // Seleccionar cualquier tipo de archivo
        startActivityForResult(intent, CODIGO_SELECCIONAR_ARCHIVO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODIGO_SELECCIONAR_ARCHIVO && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                // Aquí puedes obtener la URI del archivo seleccionado
                val rutaArchivo = uri.toString()
                Toast.makeText(requireContext(), "Archivo seleccionado: $rutaArchivo", Toast.LENGTH_SHORT).show()
                // Aquí puedes realizar más acciones con la URI, como cargar el archivo a tu base de datos
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val CODIGO_SELECCIONAR_ARCHIVO = 123 // Cualquier código que desees para identificar esta solicitud
    }
}
