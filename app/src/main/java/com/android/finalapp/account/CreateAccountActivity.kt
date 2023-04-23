package com.android.finalapp.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import com.android.finalapp.R

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var createAccountButton: Button
    private lateinit var emailEt: EditText
    private lateinit var passEt:EditText
    private lateinit var adminRadioBtn: RadioButton
    private lateinit var userRadioBtn: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createAccountButton = findViewById(R.id.create_account_button)
        emailEt = findViewById(R.id.email_edit_text)
        passEt = findViewById(R.id.password_edit_text)
        adminRadioBtn = findViewById(R.id.admin_radio_button)
        userRadioBtn = findViewById(R.id.user_radio_button)

        accountDao = AccountDatabase.getInstance(this).accountDao()

        createAccountButton.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passEt.text.toString()

            // Check which account type is selected
            val isAdmin = adminRadioBtn.isChecked
            val isUser = userRadioBtn.isChecked

            // Create the account based on the selected type
            if (isAdmin) {
                //get the email and paswword and send this to database

            }
            if (isUser){

            }
        }

    }
}