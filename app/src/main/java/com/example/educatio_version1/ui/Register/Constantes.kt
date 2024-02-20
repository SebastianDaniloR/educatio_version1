package com.example.educatio_version1.ui.Register

class Constantes {
    companion object{
        const val NOM_BD = "BdRegistro"
        const val VERSION_BD = 1
        const val TABLA = "CREATE TABLE registro (cedula TEXT PRIMARY KEY, nombre TEXT, nacimiento TEXT, ciudad TEXT, telefono TEXT, correo TEXT, contrasena TEXT)"
    }
}