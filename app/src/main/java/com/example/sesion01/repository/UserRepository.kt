package com.example.sesion01.repository

import android.content.ContentValues
import com.example.sesion01.data.db.UserDao
import com.example.sesion01.data.model.User

class UserRepository (private val userDao: UserDao){

    fun insertUser(user : User): Long{
        return userDao.insertUser(user)
    }

    fun getAllUsers():List<User>{
        return userDao.getAllUsers()
    }

    fun getUsersFilter(nameFilter:String):List<User>{
        return userDao.getUsersFilter(nameFilter)
    }

    fun updateUser(id: Long, newName: String, newEmail: String, newPhone: String, newPassword: String): Int{
        return  userDao.updateUser(id,newName,newEmail,newPhone,newPassword)
    }

    fun deleteUser(id:Long): Int{
        return userDao.deleteUser(id)
    }

    fun loginUser(email:String,password:String):Boolean{
        return userDao.authenticateUser(email,password)
    }
}