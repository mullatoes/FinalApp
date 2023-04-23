package com.android.finalapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.finalapp.model.Item

class ItemRedeemActivity : AppCompatActivity() {

    private lateinit var redeemButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_redeem)

        val retrievedItemName: TextView = findViewById(R.id.textViewItemName)
        val retrievedItemPrice: TextView = findViewById(R.id.textViewItemPrice)
        val retrievedItemDesc: TextView = findViewById(R.id.textViewItemDescription)

        redeemButton = findViewById(R.id.btnRedeem)

        val item: Item? = intent.getParcelableExtra("item")
        retrievedItemName.text = item?.itemName
        retrievedItemPrice.text = item?.itemPrice.toString()
        retrievedItemDesc.text = item?.itemDescription

        redeemButton.setOnClickListener {
            showRedeemDialog()
        }

    }

    private fun showRedeemDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_redeem, null)
        val redeemCodeEditText = view.findViewById<EditText>(R.id.redeemCodeEditText)
        val redeemButton = view.findViewById<Button>(R.id.redeemButton)

        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        redeemButton.setOnClickListener {
            val redeemCode = redeemCodeEditText.text.toString()
            // Do something with the redeem code, e.g. send it to your server
            val itemPoints = 10
            if (redeemCode.toInt() > itemPoints ) {
                Toast
                    .makeText(
                        this,
                        "Item redeemed successfully",
                        Toast.LENGTH_LONG
                    )
                    .show()
            } else {
                Toast
                    .makeText(
                        this,
                        "You cannot redeem this item. You have low points than the required",
                        Toast.LENGTH_LONG
                    )
                    .show()
            }
            dialog.dismiss()
        }

        dialog.show()
    }
}