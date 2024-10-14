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

    fun updateUser(id: Long, newName: String): Int{
        return  userDao.updateUser(id,newName)
    }

    fun deleteUser(id:Long): Int{
        return userDao.deleteUser(id)
    }
}