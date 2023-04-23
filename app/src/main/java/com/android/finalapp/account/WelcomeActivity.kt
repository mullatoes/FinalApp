package com.android.finalapp.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.finalapp.R

class WelcomeActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var createAccountBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        loginBtn = findViewById(R.id.btn_login_welcome)
        createAccountBtn = findViewById(R.id.btn_create_account_welcome)

        loginBtn.setOnClickListener {
            val intent = Intent(this, UniversalLoginActivity::class.java)
            startActivity(intent)
        }

        createAccountBtn.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

    }
}