package com.android.finalapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.android.finalapp.data.AppDatabase
import com.android.finalapp.model.Item
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemFormActivity : AppCompatActivity() {

    private lateinit var editTextItemName: EditText
    private lateinit var editTextItemPrice: EditText
    private lateinit var editTextItemDescription: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var itemImage: ImageView
    private var selectedImageUri: Uri? = null

    companion object {
        private const val REQUEST_IMAGE_PICK = 1
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_form)

        editTextItemName = findViewById(R.id.editTextItemName)
        editTextItemPrice = findViewById(R.id.editTextItemPrice)
        editTextItemDescription = findViewById(R.id.editTextItemDescription)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        itemImage = findViewById(R.id.item_image)

        // Get a reference to the Firestore database
        val db = Firebase.firestore
        // Get a reference to the "items" collection
        val itemsCollection = db.collection("items")

        // Create a new document in the "items" collection with a unique ID
        val newItemRef = itemsCollection.document()

        itemImage.setOnClickListener {
            // Open the gallery to pick an image
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        buttonSubmit.setOnClickListener {
            // Get the values from the form fields
            val itemName = editTextItemName.text.toString()
            val itemPrice = editTextItemPrice.text.toString().toDouble()
            val itemDescription = editTextItemDescription.text.toString()

            val item = Item(
                itemName = itemName,
                itemPrice = itemPrice,
                itemDescription = itemDescription,
                imageUri = selectedImageUri!!.toString()

            )
            // Set the data of the document to the item object
            newItemRef.set(item)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Item added successfully", Toast.LENGTH_SHORT
                    )
                        .show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Error adding items ${it.localizedMessage}", Toast.LENGTH_SHORT
                    )
                        .show()
                }

//            val itemDao = AppDatabase.getInstance(this).itemDao()
//            GlobalScope.launch {
//                itemDao.insert(item)
//            }

            editTextItemName.text.clear()
            editTextItemDescription.text.clear()
            editTextItemPrice.text.clear()

            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            selectedImageUri = data.data
            // Set the image to the ImageView
            itemImage.setImageURI(selectedImageUri)
        }
    }
}