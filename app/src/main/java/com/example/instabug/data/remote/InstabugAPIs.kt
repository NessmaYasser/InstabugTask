package com.example.instabug.data.remote

import java.net.URL

class InstabugAPIs {

    fun getInstabugWords(url : String) = URL(url).readText()

}