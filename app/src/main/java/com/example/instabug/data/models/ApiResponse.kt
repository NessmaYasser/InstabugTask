package com.example.instabug.data.models

sealed class ApiResponse <T>{
    data class Success<T>(var body : T) :ApiResponse<T>()
    data class Error<T>(var errorMessage : String?, var errorCode : Int): ApiResponse<T>()
    data class NetworkError<T>(var errorMessage: String?): ApiResponse<T>()
    data class ConnectionTimeOut<T>(var errorMessage: String?):ApiResponse<T>()
}