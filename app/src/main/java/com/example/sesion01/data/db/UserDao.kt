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
            put("email",user.email)
            put("phone",user.phone)
            put("password",hashPassword(user.password))
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
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
                users.add(User(id,name,email,phone,password))
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
        try {
            val projection = arrayOf("id","name","email","phone","password")
            val selection = "name LIKE ?"
            val selectionArgs = arrayOf("$nameFilter%")
            val sortOrder = "id DESC"


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
                    val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))

                    // Procesar los datos obtenidos
                    users.add(User(id, name, email,phone,password))
                } while (cursor.moveToNext())
            }
            return users

        }catch (e: Exception){
            Log.d("SEGUIMIENTO", e.toString())
            return users
        }

    }

    fun updateUser(id: Long, newName: String, newEmail:String, newPhone:String,  newPassword:String): Int{
        val db = dbHelper.readableDatabase
        val values = ContentValues().apply {
            put("name",newName)
            put("email",newEmail)
            put("phone",newPhone)
            put("password",hashPassword(newPassword))
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

    fun authenticateUser(email:String, password: String):Boolean{
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "users",
            arrayOf("id"),
            "email = ? AND password = ?",
            arrayOf(email,hashPassword(password)),
            null,
            null,
            null

        )
        val isAuthenticated = cursor.moveToFirst()
        cursor.close()
        return isAuthenticated
    }

    private fun hashPassword(password:String):String{
        return password.hashCode().toString() //encriptación simple
    }

}