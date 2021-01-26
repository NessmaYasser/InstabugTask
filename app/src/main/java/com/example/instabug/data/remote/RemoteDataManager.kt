package com.example.instabug.data.remote

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection

class RemoteDataManager(val instabugAPIs: InstabugAPIs) {

    fun getInstabugWords() = instabugAPIs.getInstabugWords()

}