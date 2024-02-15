package edu.temple.convoy

import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.launch
import java.net.URL

val BASE_USER_REGLOG_URL = " https://kamorris.com/lab/convoy/account.php"
val SERVER_RESPONSE_USER = "ServerResponseUser"
val EXCEPTION_THROWN = "ExceptionThrown"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("APIKEY", "apikey = ${BuildConfig.GOOGLE_MAPS_API_KEY}")
            ConvoyTheme {
                var isMember by remember{ mutableStateOf(true) }
                var fname by remember { mutableStateOf("") }
                var lname by remember { mutableStateOf("") }
                var uname by remember { mutableStateOf("") }
                var pword by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isMember){
                        fname = OutlinedTextField_Composable("First name")
                        lname = OutlinedTextField_Composable("Last name")
                    }
                    uname = OutlinedTextField_Composable("Username")
                    pword = OutlinedTextField_Composable("Password")

                    val details = arrayOf( uname, fname, lname,pword)

                    if (isMember){
                        Button_Composable("Log in!", details)

                        Text(
                            text = "Don't have an account? Click here!",
                            modifier = Modifier.clickable { isMember = false }
                        )
                    }else{
                        Button_Composable("Sign up!", details)
                    }
                }
            }
        }
    }
}

@Composable
fun OutlinedTextField_Composable(nameOfLabel: String) : String {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(nameOfLabel) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colors.secondary
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (text.isEmpty()) VisualTransformation.None else VisualTransformation.None
    )
    return text
}

@Composable
fun Button_Composable(nameOfButton: String, details: Array<String>){
    val coroutineScope = rememberCoroutineScope()

    Button(onClick = {
        coroutineScope.launch {
            ServerSide().registerPOST(URL(BASE_USER_REGLOG_URL), details)
        }
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
