package com.example.instabug.data.remote

class RemoteDataManager(private val instabugAPIs: InstabugAPIs) {

    fun getInstabugWords(url : String) = instabugAPIs.getInstabugWords(url)

}