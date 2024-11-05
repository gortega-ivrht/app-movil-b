package com.example.sesion01.pantallas

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sesion01.CursoresListaActivity
import com.example.sesion01.PersistenciaActivity
import com.example.sesion01.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(viewModel:UserViewModel){
    val contex = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value= email,
            onValueChange = {email=it},
            label = { Text("Email") }
        )

        OutlinedTextField(
            value= password,
            onValueChange = {password=it},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val login = viewModel.loginUser(email,password)
                if (login){
                    val intent = Intent(
                        contex,
                        CursoresListaActivity::class.java
                    )
                    contex.startActivity(intent)
                }else{
                    error = "DATOS INCORRECTOS"
                }
            }
        ) {
           Text("Login")
        }

        error?.let { Text(it, color = Color.Red) }

        OutlinedButton(onClick = {
            Log.d("PantallaPrincipal","Click en el botón")
            val intent = Intent(
                contex,
                PersistenciaActivity::class.java
            )
            contex.startActivity(intent)
        }) {
            Text(text = "PERSISTENCIA", style = MaterialTheme.typography.bodyLarge)
        }
    }

}