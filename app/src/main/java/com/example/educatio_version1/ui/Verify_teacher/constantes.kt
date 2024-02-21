package com.example.educatio_version1.ui.Register

class constantes {
    companion object{
        const val NOM_BD = "BdVerifyTeacher"
        const val VERSION_BD = 1
        const val TABLA2 = "CREATE TABLE registro (cedula TEXT PRIMARY KEY, nombre_completo TEXT, nacimiento TEXT, ciudad TEXT, telefono TEXT, correo TEXT, contrasena TEXT,hoja_vida TEXT)"
    }
}