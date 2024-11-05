package com.example.sesion01.data.model

data class User(
    val id: Long = 0L,
    val name: String,
    val email: String ,
    val phone: String,
    val password: String
)