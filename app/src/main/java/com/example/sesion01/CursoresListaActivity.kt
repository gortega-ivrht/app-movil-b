package com.example.sesion01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sesion01.data.db.UserDao
import com.example.sesion01.pantallas.PantallaCursores
import com.example.sesion01.repository.UserRepository
import com.example.sesion01.ui.theme.Sesion01Theme
import com.example.sesion01.viewmodel.UserViewModel

class CursoresListaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userDao = UserDao(this)
        val userRepository = UserRepository(userDao)
        val userViewModel = UserViewModel(userRepository)


        setContent {
            PantallaCursores(userViewModel)
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Sesion01Theme {
        Greeting3("Android")
    }
}