package com.android.finalapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.finalapp.model.Item

class ItemRedeemActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_redeem)

        val retrievedItemName: TextView = findViewById(R.id.textViewItemName)
        val retrievedItemPrice: TextView = findViewById(R.id.textViewItemPrice)
        val retrievedItemDesc: TextView = findViewById(R.id.textViewItemDescription)
        val item: Item? = intent.getParcelableExtra("item")
        retrievedItemName.text = item?.itemName
        retrievedItemPrice.text = item?.itemPrice.toString()
        retrievedItemDesc.text = item?.itemDescription

    }
}