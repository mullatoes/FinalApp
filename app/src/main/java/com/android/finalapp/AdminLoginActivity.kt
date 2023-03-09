package com.android.finalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    companion object {
        private const val USERNAME = "admin"
        private const val PASSWORD = "password123"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        editTextUsername = findViewById(R.id.editTextUsernameAdmin)
        editTextPassword = findViewById(R.id.editTextPasswordAdmin)
        buttonLogin = findViewById(R.id.buttonLoginAdmin)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (username == USERNAME && password == PASSWORD) {
                // Navigate to admin dashboard activity
                val intent = Intent(this, AdminDashboardActivity::class.java)
                startActivity(intent)
                finish() // optional: close the login activity to prevent going back to it using back button
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}