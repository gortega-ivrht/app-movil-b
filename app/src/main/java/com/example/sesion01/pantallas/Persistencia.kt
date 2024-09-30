package com.example.sesion01.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sesion01.viewmodel.UserViewModel

@Composable
fun Persistencia(userViewModel: UserViewModel){

    var name by remember { mutableStateOf("USUARIO") }
    val userList by userViewModel.userList.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.loadUser()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        BasicTextField(
            value = name,
            onValueChange = {name = it},
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                if (name.isNotBlank()){
                    userViewModel.insertUser(name)
                    name= ""
                }
            }
        ) {
            Text("AGREGAR USUARIO")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(userList){
                user -> Text(user.name)
            }
        }
    }
}