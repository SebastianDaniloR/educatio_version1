package com.example.educatio_version1.ui.Register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.educatio_version1.databinding.FragmentRegisterBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val managerBd = ManagerBd(this)
            val resultado = managerBd.insertarDatos(cedula, nombre, fechaNacimiento, ciudad, telefono, correo, contrasena)

            // Manejar el resultado según corresponda
            if (resultado != -1L) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}