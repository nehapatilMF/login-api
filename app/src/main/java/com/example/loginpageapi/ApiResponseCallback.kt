package com.example.loginpageapi

interface ApiResponseCallback {
    fun onSuccess(response: ApiResponse)
    fun onError(error: String)
}