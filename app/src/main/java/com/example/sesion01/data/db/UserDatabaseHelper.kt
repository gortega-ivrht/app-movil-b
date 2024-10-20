package com.example.sesion01.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class UserDatabaseHelper (context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "user_database.db"
        private const val DATABASE_VERSION = 14
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)

        Log.d("onUpgrade Version db oldVersion",oldVersion.toString())
        Log.d("onUpgrade Version db newVersion",newVersion.toString())

        if (oldVersion < newVersion ){
            Log.d("onUpgrade","Agregando la columna...")
            db.execSQL("ALTER TABLE users ADD COLUMN email TEXT DEFAULT 'sin email'")
            db.execSQL("ALTER TABLE users ADD COLUMN phone TEXT DEFAULT 'sin phone'")
        }
    }

}