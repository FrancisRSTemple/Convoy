package edu.temple.convoy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import edu.temple.convoy.ui.theme.ConvoyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvoyTheme {
                var isMember by remember{ mutableStateOf(true) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isMember) OutlinedTextField_Composable("Full name")
                    OutlinedTextField_Composable("Username")
                    OutlinedTextField_Composable("Password")

                    if (isMember){
                        Button_Composable("Log in!")

                        Text(
                            text = "Don't have an account?",
                            modifier = Modifier.clickable { isMember = true }
                        )
                    }else{
                        Button_Composable("Sign up!")
                    }
                }
            }
        }
    }
}

@Composable
fun OutlinedTextField_Composable(nameOfLabel : String){
    var text by remember{ mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        label = { Text(nameOfLabel)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colors.secondary
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        visualTransformation = if(text.isEmpty()) VisualTransformation.None else VisualTransformation.None
    )
}

@Composable
fun Button_Composable(nameOfButton : String){
    Button(onClick = {

    }) {
        Text(text = nameOfButton)
    }
}

@Composable
fun Text_Composable(nameOfButton : String){
    Button(onClick = {

    }) {
        Text(text = nameOfButton)
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConvoyTheme {
        Greeting("Android")
    }
}*/
