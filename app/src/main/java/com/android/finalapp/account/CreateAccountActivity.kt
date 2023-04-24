package com.android.finalapp.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.android.finalapp.AdminDashboardActivity
import com.android.finalapp.R
import com.android.finalapp.RedemptionActivity
import com.android.finalapp.account.entities.Admin
import com.android.finalapp.account.entities.User
import com.android.finalapp.data.AccountDao
import com.android.finalapp.data.AppDatabase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var createAccountButton: Button
    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var adminRadioBtn: RadioButton
    private lateinit var userRadioBtn: RadioButton

    private lateinit var accountDao: AccountDao

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createAccountButton = findViewById(R.id.create_account_button)
        emailEt = findViewById(R.id.email_edit_text)
        passEt = findViewById(R.id.password_edit_text)
        adminRadioBtn = findViewById(R.id.admin_radio_button)
        userRadioBtn = findViewById(R.id.user_radio_button)

        accountDao = AppDatabase.getInstance(this).accountDao()

        mAuth = FirebaseAuth.getInstance()

        createAccountButton.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passEt.text.toString()

            // Check which account type is selected
            val isAdmin = adminRadioBtn.isChecked
            val isUser = userRadioBtn.isChecked

            // Create the account based on the selected type
            if (isAdmin) {
                //get the email and password and send this to database
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(
                                Intent(
                                    this@CreateAccountActivity,
                                    AdminDashboardActivity::class.java
                                )
                            )
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Authentication failed", Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Authentication failed ${it.localizedMessage}", Toast.LENGTH_SHORT
                        )
                            .show()
                    }
//                val admin = Admin(email, password)
//                GlobalScope.launch(Dispatchers.IO) {
//                    accountDao.insertAdmin(admin)
//
//                    // Redirect the admin to the admin dashboard
//                    withContext(Dispatchers.Main) {
//                        startActivity(Intent(this@CreateAccountActivity, AdminDashboardActivity::class.java))
//                        finish() // Finish the current activity to prevent going back
//                    }
//                }
            }

            if (isUser) {
                // Create a user account
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(
                                Intent(
                                    this@CreateAccountActivity,
                                    RedemptionActivity::class.java
                                )
                            )
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Auth failed", Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Auth failed ${it.localizedMessage}", Toast.LENGTH_SHORT
                        )
                            .show()
                    }
//                val user = User(email, password)
//                GlobalScope.launch(Dispatchers.IO) {
//                    accountDao.insertUser(user)
//
//                    withContext(Dispatchers.Main) {
//                        startActivity(Intent(this@CreateAccountActivity, RedemptionActivity::class.java))
//                        finish() // Finish the current activity to prevent going back
//                    }
//                }
            }
        }

    }
}