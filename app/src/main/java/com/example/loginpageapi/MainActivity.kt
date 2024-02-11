package com.example.loginpageapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.logout_button)
        button.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        ApiManager.logout(object : ApiResponseCallback {
            override fun onSuccess(response: ApiResponse) {
                Log.d("MainActivity", "Response: ${response.message}")
                // Perform necessary actions on successful logout
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onError(error: String) {
                Log.e("MainActivity", "Error: $error")
            }
        })
    }
}








