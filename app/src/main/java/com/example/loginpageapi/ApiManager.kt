package com.example.loginpageapi

    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory

    object ApiManager {

        private const val BASE_URL = "https://mutefrog.com/mobile_test/login.php/"
    /// define Retrofit instance
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//// create API service instance
        private val apiService: ApiService = retrofit.create(ApiService::class.java)

        fun login(username: String, password: String, callback: ApiResponseCallback) {
            val call = apiService.login(username, password)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            callback.onSuccess(apiResponse)
                        } else {
                            callback.onError("Response body is null")
                        }
                    } else {
                        callback.onError("Login failed")
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    callback.onError(t.message ?: "Login request failed")
                }
            })
        }

        fun logout(callback: ApiResponseCallback) {
            val call = apiService.logout()
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            callback.onSuccess(apiResponse)
                        } else {
                            callback.onError("Response body is null")
                        }
                    } else {
                        callback.onError("Logout failed")
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    callback.onError(t.message ?: "Logout request failed")
                }
            })
        }
    }

