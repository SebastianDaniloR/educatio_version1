package com.example.educatio_version1.ui.Verify_teacher

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class Managerbd(context: Context) {
    private val bdHelper = bdHelper(context)
    private var bd: SQLiteDatabase? = null
    private val preferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)


    fun abrirBdEscritura() {
        bd = bdHelper.writableDatabase
    }

    fun abrirBdLectura() {
        bd = bdHelper.readableDatabase
    }

    fun cerrarBd() {
        bd?.close()
    }

    fun insertarDatos(cedula: String, nombre: String, nacimiento: String, ciudad: String, telefono: String, correo: String, contrasena: String, hojaVida: String): Long {
        abrirBdEscritura()
        val contenedor = ContentValues().apply {
            put("cedula", cedula)
            put("nombre_completo", nombre)
            put("nacimiento", nacimiento)
            put("ciudad", ciudad)
            put("telefono", telefono)
            put("correo", correo)
            put("contrasena", contrasena)
            put("hoja_vida", hojaVida)
        }
        val result = bd?.insert("docente", null, contenedor) // Cambia "docente" por el nombre de tu tabla
        if (result != null && result != -1L) {
            // Almacenar el correo y la contraseña en SharedPreferences
            preferences.edit().apply {
                putString("correo", correo)
                putString("contrasena", contrasena)
                apply()
            }
        }
        cerrarBd()
        return result ?: -1
    }


    fun actualizarDatos(cedula: String, nombre: String, nacimiento: String, ciudad: String, telefono: String, correo: String, contrasena: String, hojaVida: String): Int {
        abrirBdEscritura()
        val contenedor = ContentValues().apply {
            put("nombre_completo", nombre)
            put("nacimiento", nacimiento)
            put("ciudad", ciudad)
            put("telefono", telefono)
            put("correo", correo)
            put("contrasena", contrasena)
            put("hoja_vida", hojaVida)
        }
        val result = bd?.update(constantes.TABLA2, contenedor, "cedula = ?", arrayOf(cedula))
        cerrarBd()
        return result ?: -1
    }


    fun eliminarDatos(cedula: String): Int {
        abrirBdEscritura()
        val result = bd?.delete(constantes.TABLA2, "cedula = ?", arrayOf(cedula))
        cerrarBd()
        return result ?: -1
    }

    fun obtenerDatos(cedula: String): Cursor? {
        abrirBdLectura()
        val cursor = bd?.query(constantes.TABLA2, null, "cedula = ?", arrayOf(cedula), null, null, null)
        cursor?.moveToFirst()
        cerrarBd()
        return cursor
    }

    fun obtenerDatosPorCorreo(correo: String): Cursor? {
        abrirBdLectura()
        val cursor = bd?.query("docente", null, "correo = ?", arrayOf(correo), null, null, null)
        cursor?.moveToFirst()
        return cursor
    }

    fun insertarHojaVida(rutaArchivo: String): Long {
        abrirBdEscritura()
        val contenedor = ContentValues().apply {
            put("hoja_vida", rutaArchivo)
        }
        val result = bd?.insert("docente", null, contenedor) // Cambia "docente" por el nombre de tu tabla
        cerrarBd()
        return result ?: -1
    }



}
