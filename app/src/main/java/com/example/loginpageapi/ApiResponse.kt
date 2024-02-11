package com.example.loginpageapi

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class ApiResponse(val message: String)
/// define API interface
interface ApiService {
    ///making login request using @POST method
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Call<ApiResponse>
    //making logout request using @POST method
    @POST("logout.php")
    fun logout(): Call<ApiResponse>
}