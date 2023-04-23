package com.android.finalapp

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.android.finalapp.data.AppDatabase
import com.android.finalapp.model.Item
import com.android.finalapp.model.Rating
import com.android.finalapp.repository.ItemRepository
import com.android.finalapp.viewmodel.ItemViewModel
import com.android.finalapp.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddRatingActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel
    private lateinit var itemImage: ImageView
    private lateinit var itemPrice: TextView
    private lateinit var itemName: TextView
    private lateinit var itemDescription: TextView
    private lateinit var btnSubmit: Button
    private lateinit var ratingBar: RatingBar
    private lateinit var addCommentEt: EditText

    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rating)

        val repository = ItemRepository(AppDatabase.getInstance(applicationContext).itemDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ItemViewModel::class.java]

        val itemId = intent.getIntExtra("itemId", -1)

        itemName = findViewById(R.id.textViewItemName_rate)
        itemPrice = findViewById(R.id.textViewItemPrice_rate)
        itemDescription = findViewById(R.id.textViewItemDescription_rate)
        itemImage = findViewById(R.id.imageViewItem_rate)
        btnSubmit = findViewById(R.id.btn_submit_rate)
        ratingBar = findViewById(R.id.ratingBar_rate)
        addCommentEt = findViewById(R.id.et_add_comment_rate)

        viewModel.getItemById(itemId)

        viewModel.itemLiveData.observe(this) { fetchedItem ->
            Toast.makeText(
                this@AddRatingActivity,
                "Name: ${fetchedItem!!.itemName}",
                Toast.LENGTH_LONG
            )
                .show()

            itemName.text = fetchedItem!!.itemName
            itemPrice.text = "$${fetchedItem!!.itemPrice}"
            itemDescription.text = fetchedItem!!.itemDescription
            Glide.with(this@AddRatingActivity)
                .load(fetchedItem.imageUri)
                .apply(RequestOptions().placeholder(R.drawable.product_24))
                .timeout(6000)
                .into(itemImage)
        }

        val itemDao = AppDatabase.getInstance(this).itemDao()

        btnSubmit.setOnClickListener {
            val rating = ratingBar.rating
            val comment = addCommentEt.text.toString()
            val newRating = Rating(
                itemId = itemId,
                ratingValue = rating.toInt(),
                comment = comment
            )

            GlobalScope.launch {
                itemDao.insertRating(newRating)
            }

            val intent = Intent(this@AddRatingActivity, RedemptionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}