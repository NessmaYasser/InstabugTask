package com.example.instabug.data.remote

class RemoteDataManager(private val instabugAPIs: InstabugAPIs) {

    fun getInstabugWords() = instabugAPIs.getInstabugWords()

}