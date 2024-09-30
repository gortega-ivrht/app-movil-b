package com.example.sesion01

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sesion01.data.db.UserDao
import com.example.sesion01.pantallas.PantallaPrincipal
import com.example.sesion01.pantallas.Persistencia
import com.example.sesion01.repository.UserRepository
import com.example.sesion01.ui.theme.Sesion01Theme
import com.example.sesion01.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val  userDao = UserDao(this)
        val userRepository = UserRepository(userDao)
        val userViewModel = UserViewModel(userRepository)

        setContent {
            Persistencia(userViewModel)
        }

    }


    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"MAIN onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"MAIN onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this,"MAIN onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this,"MAIN onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"MAIN onDestroy", Toast.LENGTH_SHORT).show()
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Sesion01Theme {
        PantallaPrincipal()
    }
}