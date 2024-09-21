package com.example.sesion01.pantallas

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.sesion01.R

@Composable
fun PantallaEventosDos(){
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .offset(x = offset.x.dp, y = offset.y.dp)
            .pointerInput(Unit){
                detectDragGestures { change, dragAmount ->

                    offset += dragAmount
                    change.consumeAllChanges()

                }
            }
    ){
        Image(painter = painterResource(id = R.drawable.dos), contentDescription = "LOGO")
    }

}

@OptIn(ExperimentalComposeUiApi::class,ExperimentalMaterial3Api::class)
@Composable
fun PantallaEventosTres(){

    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    TextField(
        value = text,
        onValueChange = {text = it},
        label = { Text("Escribe algo") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()

                if (text.isNotBlank()){
                    Log.d("Teclado","NO ES VACIO " + text)
                }else{
                    Log.d("Teclado","SI ES VACIO")
                }

            }
        )
    )
}