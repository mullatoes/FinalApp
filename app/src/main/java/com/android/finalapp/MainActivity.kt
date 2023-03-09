package com.android.finalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAdmin = findViewById<Button>(R.id.buttonAdmin)
        val buttonUser = findViewById<Button>(R.id.buttonUser)

        buttonAdmin.setOnClickListener {
            // Navigate to admin login activity
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
        }

        buttonUser.setOnClickListener {
            // Navigate to user login activity
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
        }
    }
}