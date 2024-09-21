package com.example.sesion01.pantallas

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sesion01.MainActivity

@Composable
fun PantallaEventos (){
    var boxColor by remember { mutableStateOf(Color.Gray) }
    var buttonText by remember { mutableStateOf("Click Aquí") }

    Box(
        modifier = Modifier
            .size(150.dp)
            .background(boxColor)
            .pointerInput(Unit){
                detectTapGestures (
                    onPress = {
                        boxColor = Color.Red
                        awaitRelease()
                        boxColor = Color.Gray
                    },
                    onLongPress = {
                        boxColor = Color.Blue
                    }
                )
            }
    )
    Button(
        onClick = {
            buttonText = "Click Realizado"
            Log.d("Botón","Hiciste un click")
        }
    ) {
        Text(buttonText)
    }

}


@Composable
fun PantallaEventosOrdenado(){
    val contex = LocalContext.current

    var boxColor by remember { mutableStateOf(Color.Gray) }
    var buttonText by remember { mutableStateOf("Click Aquí") }

    Column (
        modifier = Modifier
            .size(150.dp)
            .background(boxColor)
            .pointerInput(Unit){
                detectTapGestures (
                    onPress = {
                        boxColor = Color.Red
                        awaitRelease()
                        boxColor = Color.Gray
                    },
                    onLongPress = {
                        boxColor = Color.Blue
                    }
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(text = "Hola desde presentar")

        Button(
            onClick = {
                buttonText = "Click Realizado"
                Log.d("Botón","Hiciste un click")
            }
        ) {
            Text(buttonText)
        }

        OutlinedButton(onClick = {
            Log.d("PantallaPresentar","Click en el botón regresar")
            val intent = Intent(
                contex,
                MainActivity::class.java
            )
            contex.startActivity(intent)
        }) {
            Text(text = "Volver", style = MaterialTheme.typography.bodyLarge)
        }
    }

}