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
import com.android.finalapp.data.AccountDao
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UniversalLoginActivity : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var isAdminRb: RadioButton
    private lateinit var isUserRb: RadioButton
    private lateinit var loginButton: Button
    private lateinit var accountDao: AccountDao
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_universal_login)

        emailEt = findViewById(R.id.email_edit_text_universalLogin)
        passEt = findViewById(R.id.password_edit_text_universalLogin)
        isAdminRb = findViewById(R.id.admin_radio_button_universalLogin)
        isUserRb = findViewById(R.id.user_radio_button_universalLogin)
        loginButton = findViewById(R.id.login_button_universalLogin)

        mAuth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passEt.text.toString()

            // Check which account type is selected
            val isAdmin = isAdminRb.isChecked

            if (isAdmin) {

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Redirect the admin to the admin dashboard
                            startActivity(
                                Intent(
                                    this@UniversalLoginActivity,
                                    AdminDashboardActivity::class.java
                                )
                            )
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(
                            this, "Sign In Failed $it",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
//                GlobalScope.launch(Dispatchers.IO) {
//                    val admin = accountDao.getAdminByEmailAndPassword(email, password)
//                    withContext(Dispatchers.Main) {
//                        if (admin != null) {
//                            // Redirect the admin to the admin dashboard
//                            startActivity(
//                                Intent(
//                                    this@UniversalLoginActivity,
//                                    AdminDashboardActivity::class.java
//                                )
//                            )
//                            finish() // Finish the current activity to prevent going back
//                        } else {
//                            // Display an error message if the admin credentials are invalid
//                            Toast.makeText(
//                                this@UniversalLoginActivity,
//                                "Invalid credentials",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Redirect the admin to the admin dashboard
                            startActivity(
                                Intent(
                                    this@UniversalLoginActivity,
                                    RedemptionActivity::class.java
                                )
                            )
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(
                            this, "Sign In Failed $it",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
//                GlobalScope.launch(Dispatchers.IO) {
//                    val user = accountDao.getUserByEmailAndPassword(email, password)
//                    withContext(Dispatchers.Main) {
//                        if (user != null) {
//                            // Redirect the user to the user dashboard
//                            startActivity(
//                                Intent(
//                                    this@UniversalLoginActivity,
//                                    RedemptionActivity::class.java
//                                )
//                            )
//                            finish()
//                        } else {
//                            // Display an error message if the admin credentials are invalid
//                            Toast.makeText(
//                                this@UniversalLoginActivity,
//                                "Invalid credentials",
//                                Toast.LENGTH_SHORT
//                            ).show()
//
//                        }
//                    }
//                }

            }
        }
    }
}