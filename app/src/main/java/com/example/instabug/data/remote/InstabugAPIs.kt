package com.example.instabug.data.remote

import com.example.instabug.data.models.ApiResponse
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.text.Charsets.UTF_8

class InstabugAPIs {
//    fun getInstabugWords() = URL(ApiUrls.BASE_URL).readText()

    fun getInstabugWords(): String {
        val url = URL(ApiUrls.BASE_URL)
        var httpURLConnection: HttpURLConnection? = null
        httpURLConnection = url.openConnection() as HttpURLConnection
        val rc = httpURLConnection.responseCode
        if (rc != HttpURLConnection.HTTP_OK) {
            throw Exception("Error: ${rc}")
        }
        val inp: InputStream = BufferedInputStream(httpURLConnection.inputStream)
        val resp: String = inp.bufferedReader(UTF_8).use { it.readText() }
        httpURLConnection.disconnect()
        return resp


    }
}