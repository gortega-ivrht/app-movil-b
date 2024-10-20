package com.example.sesion01.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.sesion01.data.model.User

class UserDao (context: Context){
    private val dbHelper = UserDatabaseHelper(context)

    fun insertUser(user: User): Long{

        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name",user.name)
        }

        return db.insert("users",null,values).also {
            db.close()
        }

    }

    fun getAllUsers() : List<User>{
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("users",null,null,null,null,null,null)
        val users = mutableListOf<User>()

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                users.add(User(id,name))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return users
    }

    fun getUsersFilter(nameFilter : String): List<User>{
        val db = dbHelper.readableDatabase
        val users = mutableListOf<User>()

        // select id,name from users
        // where name LIKE "ma%"
        // order by id DESCC

        // gean, Pedro, maria, mafer, matia, marycielo, romario = mar

        val projection = arrayOf("id","name","email","phone")
        val selection = "name LIKE ?"
        val selectionArgs = arrayOf("$nameFilter%")
        val sortOrder = "id DESC"

        try {
            val cursor : Cursor = db.query("users",
                projection,
                selection,
            selectionArgs,
            null,
            null,
            sortOrder)
            Log.d("SEGUIMIENTO", cursor.toString())
            if (cursor.moveToFirst()){
                do {
                    //lógica recuperar la info
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                    val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))

                    // Procesar los datos obtenidos
                    users.add(User(id, name, email,phone))
                } while (cursor.moveToNext())
            }
            return users

        }catch (e: Exception){
            Log.d("SEGUIMIENTO", e.toString())
            return users
        }




    }

    fun updateUser(id: Long, newName: String): Int{
        val db = dbHelper.readableDatabase
        val values = ContentValues().apply {
            put("name",newName)
        }

        val selection = "id = ?"
        val selectionArgs = arrayOf(id.toString())
        return db.update("users",values,selection,selectionArgs)


    }

    fun deleteUser(id:Long): Int{
        val db = dbHelper.readableDatabase
        val selection = "id = ?"
        val selectionArgs = arrayOf(id.toString())
        return db.delete("users",selection,selectionArgs)
    }

}