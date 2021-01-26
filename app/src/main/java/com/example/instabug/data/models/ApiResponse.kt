package com.example.instabug.data.models

sealed class ApiResponse <T>{
    data class Success<T>(var body : T) :ApiResponse<T>()
    data class Error<T>(var errorMessage : String?): ApiResponse<T>()
}