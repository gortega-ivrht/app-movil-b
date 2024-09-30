package com.example.sesion01.pantallas

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.sesion01.PersistenciaActivity
import com.example.sesion01.PresentarActivity
import com.example.sesion01.R

@Composable 
fun PantallaPrincipal(){
    val contex = LocalContext.current
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.dos), contentDescription = "LOGO")
        Text(text = "Bienvenidos",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error
        )
        OutlinedButton(onClick = {
            Log.d("PantallaPrincipal","Click en el botón")
            val intent = Intent(
                contex,
                PresentarActivity::class.java
            )
            contex.startActivity(intent)
        }) {
            Text(text = "Continuar", style = MaterialTheme.typography.bodyLarge)
        }

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