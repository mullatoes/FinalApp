package com.android.finalapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.finalapp.data.AppDatabase
import com.android.finalapp.model.Item
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemFormActivity : AppCompatActivity() {

    private lateinit var editTextItemName: EditText
    private lateinit var editTextItemPrice: EditText
    private lateinit var editTextItemDescription: EditText
    private lateinit var buttonSubmit: Button

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_form)

        editTextItemName = findViewById(R.id.editTextItemName)
        editTextItemPrice = findViewById(R.id.editTextItemPrice)
        editTextItemDescription = findViewById(R.id.editTextItemDescription)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            // Get the values from the form fields
            val itemName = editTextItemName.text.toString()
            val itemPrice = editTextItemPrice.text.toString().toDouble()
            val itemDescription = editTextItemDescription.text.toString()

            val item = Item(
                itemName = itemName,
                itemPrice = itemPrice,
                itemDescription = itemDescription
            )

            val itemDao = AppDatabase.getInstance(this).itemDao()
            GlobalScope.launch {
                itemDao.insert(item)
            }

            editTextItemName.text.clear()
            editTextItemDescription.text.clear()
            editTextItemPrice.text.clear()

            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)

        }

    }
}