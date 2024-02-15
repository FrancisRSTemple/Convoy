package edu.temple.convoy

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.ResponseCache
import java.net.URL

class ServerSide {
    suspend fun registerPOST(baseUrl : URL, details: Array<String>){
        return withContext(Dispatchers.IO){
            val registerUrl = "$baseUrl/REGISTER"
            val registerData = "username=${details[0]}&firstname=${details[1]}&lastname=${details[2]}&password=${details[3]}"
            Log.d(SERVER_RESPONSE_USER, doPOSTRequest(registerUrl, registerData))
        }
    }

    private fun doPOSTRequest(url:String, data:String): String {
        var responseCode = -2

        try {
            val connection = URL(url).openConnection() as HttpURLConnection

            //setup connection
            connection.requestMethod = "POST"
            connection.doOutput = true

            //write POST and close the stream
            connection.outputStream.run {
                flush()
                close()
            }

            responseCode = connection.responseCode
            if(responseCode == HttpURLConnection.HTTP_OK){
                val inputStream = connection.inputStream
                val response = java.lang.StringBuilder()
                var line: String?
                BufferedReader(InputStreamReader(inputStream)).run {

                    while(this.readLine().also { line = it } != null){
                        response.append(line)
                    }
                    close()
                }
                inputStream.close()
            }else{
                Log.d(SERVER_RESPONSE_USER, "$responseCode")
            }
            connection.disconnect()
        }catch (e: Exception){
            Log.d(EXCEPTION_THROWN, e.toString())
        }
        return responseCode.toString()
    }
}