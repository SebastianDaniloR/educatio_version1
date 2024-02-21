package com.example.educatio_version1.ui.Register

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.preference.PreferenceManager

class ManagerBd(context: Context) {
    private val bdHelper = BdHelper(context)
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

    fun insertarDatos(cedula: String, nombre: String, nacimiento: String, ciudad: String, telefono: String, correo: String, contrasena: String): Long {
        abrirBdEscritura()
        val contenedor = ContentValues().apply {
            put("cedula", cedula)
            put("nombre", nombre)
            put("nacimiento", nacimiento)
            put("ciudad", ciudad)
            put("telefono", telefono)
            put("correo", correo)
            put("contrasena", contrasena)
        }
        val result = bd?.insert("registro", null, contenedor)
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

    fun actualizarDatos(cedula: String, nombre: String, nacimiento: String, ciudad: String, telefono: String, correo: String, contrasena
    : String): Int {
        abrirBdEscritura()
        val contenedor = ContentValues().apply {
            put("nombre", nombre)
            put("nacimiento", nacimiento)
            put("ciudad", ciudad)
            put("telefono", telefono)
            put("correo", correo)
            put("contrasena", contrasena
            )
        }
        val result = bd?.update("registro", contenedor, "cedula = ?", arrayOf(cedula))
        cerrarBd()
        return result ?: -1
    }

    fun eliminarDatos(cedula: String): Int {
        abrirBdEscritura()
        val result = bd?.delete("registro", "cedula = ?", arrayOf(cedula))
        cerrarBd()
        return result ?: -1
    }

    fun obtenerDatos(cedula: String): Cursor? {
        abrirBdLectura()
        val cursor = bd?.query("registro", null, "cedula = ?", arrayOf(cedula), null, null, null)
        cursor?.moveToFirst()
        cerrarBd()
        return cursor
    }

    fun obtenerDatosPorCorreo(correo: String): Cursor? {
        abrirBdLectura()
        val cursor = bd?.query("registro", null, "correo = ?", arrayOf(correo), null, null, null)
        cursor?.moveToFirst()
        return cursor
    }

}
