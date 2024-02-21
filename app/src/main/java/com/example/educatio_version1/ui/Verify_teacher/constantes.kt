package com.example.educatio_version1.ui.Verify_teacher

class constantes {
    companion object{
        const val NOM_BD = "BdVerifyTeacher"
        const val VERSION_BD = 2
        const val TABLA2 = "CREATE TABLE docente (cedula TEXT PRIMARY KEY, nombre_completo TEXT, nacimiento TEXT, ciudad TEXT, telefono TEXT, correo TEXT, contrasena TEXT,hoja_vida TEXT)"
    }
}