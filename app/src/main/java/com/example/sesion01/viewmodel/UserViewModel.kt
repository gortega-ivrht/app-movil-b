package com.example.sesion01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sesion01.data.model.User
import com.example.sesion01.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel (private val userRepository: UserRepository) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    fun insertUser(name:String){
        viewModelScope.launch (Dispatchers.IO){
            val newUser = User(name= name)
            userRepository.insertUser(newUser)

            _userList.value = userRepository.getAllUsers()
        }
    }

    fun loadUser(){
        viewModelScope.launch (Dispatchers.IO){
            _userList.value = userRepository.getAllUsers()
        }
    }

    fun filterUsersByName(nameFilter:String){
        viewModelScope.launch(Dispatchers.IO) {
            _userList.value = userRepository.getUsersFilter(nameFilter)
        }
    }
}

