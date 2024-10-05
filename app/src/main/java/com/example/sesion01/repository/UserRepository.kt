package com.example.sesion01.repository

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
}