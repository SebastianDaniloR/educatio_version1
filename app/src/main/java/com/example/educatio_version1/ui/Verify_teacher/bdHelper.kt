package com.example.educatio_version1.ui.Register


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class bdHelper(context: Context) : SQLiteOpenHelper(context, constantes.NOM_BD, null, constantes.VERSION_BD) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(constantes.TABLA2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ciudad")
        onCreate(db)
    }
}