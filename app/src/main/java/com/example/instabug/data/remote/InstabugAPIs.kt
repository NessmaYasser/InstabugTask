package com.example.instabug.data.remote

import java.net.URL

class InstabugAPIs {

    fun getInstabugWords() = URL(ApiUrls.BASE_URL).readText()

}