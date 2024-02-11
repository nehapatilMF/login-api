package com.example.loginpageapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.etEmailAddress)
        etPassword = findViewById(R.id.etPassword)
        val loginButton: Button = findViewById(R.id.button)
        loginButton.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            login(email, password)
        }
    }
    private fun login(email: String, password: String) {
        ApiManager.login(email, password, object : ApiResponseCallback {
           override fun onSuccess(response: ApiResponse) {
                if (response.message == "Login Successfully") {
                    // Save user session or perform necessary actions on successful login
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, response.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onError(error: String) {
                Log.e("LoginActivity", "Error: $error")
                Toast.makeText(this@LoginActivity, "Please check your internet connection ", Toast.LENGTH_LONG).show()
            }
        })
    }
}