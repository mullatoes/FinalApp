package com.android.finalapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.finalapp.data.SampleData
import com.android.finalapp.model.Item

class AdminDashboardActivity : AppCompatActivity() {

    companion object {
        const val ITEM_FORM_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val buttonUploadItemAdmin: Button = findViewById(R.id.buttonUploadItemAdmin)

        buttonUploadItemAdmin.setOnClickListener {
            val intent = Intent(this, ItemFormActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ITEM_FORM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val itemName = data?.getStringExtra("itemName")
            val itemPrice = data?.getDoubleExtra("itemPrice", 0.0)
            val itemDescription = data?.getStringExtra("itemDescription")

            // Add the item to the list of items
            // ...
            SampleData.items.add(Item(itemName!!, itemPrice!!, itemDescription!!))
            Log.i("ITEMS", "Items size is ${SampleData.items.size}")

        }
    }
}