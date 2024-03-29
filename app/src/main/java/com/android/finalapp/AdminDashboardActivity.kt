package com.android.finalapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.finalapp.account.UniversalLoginActivity
import com.android.finalapp.adapter.ItemListAdapter
import com.android.finalapp.data.AppDatabase
import com.android.finalapp.data.SampleData
import com.android.finalapp.model.Item
import com.android.finalapp.repository.ItemRepository
import com.android.finalapp.viewmodel.ItemViewModel
import com.android.finalapp.viewmodel.ViewModelFactory
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminDashboardActivity : AppCompatActivity() {

    companion object {
        const val ITEM_FORM_REQUEST_CODE = 1
    }

    private lateinit var viewModel: ItemViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemList: MutableList<Item>
    private lateinit var itemsCollection: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val db = Firebase.firestore

        itemsCollection = db.collection("items")

        itemList = mutableListOf()

        val repository = ItemRepository(AppDatabase.getInstance(applicationContext).itemDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ItemViewModel::class.java]

        val buttonUploadItemAdmin: Button = findViewById(R.id.buttonUploadItemAdmin)
        val buttonLoadItems: Button = findViewById(R.id.buttonLoadItemsAdmin)
        val buttonLogOut: Button = findViewById(R.id.buttonLogout)
        recyclerView = findViewById(R.id.recyclerview_admin)

        buttonUploadItemAdmin.setOnClickListener {
            val intent = Intent(this, ItemFormActivity::class.java)
            startActivity(intent)
        }

        buttonLoadItems.setOnClickListener {
            loadItemCount()
        }

        buttonLogOut.setOnClickListener {
            val intent = Intent(this, UniversalLoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadItemCount() {
        itemsCollection.get().addOnSuccessListener { result ->
            for (document in result) {
                val item = document.toObject<Item>()
                itemList.add(item)
            }
            // Update RecyclerView adapter with itemList here
            val adapter = ItemListAdapter(
                itemList,
                itemDao = AppDatabase.getInstance(applicationContext).itemDao()
            )
            recyclerView.layoutManager =
                LinearLayoutManager(this@AdminDashboardActivity)
            recyclerView.adapter = adapter
        }
//        CoroutineScope(Dispatchers.IO).launch {
//            val size = viewModel.getAllItems().size
//            val itemList = viewModel.getAllItems()
//            lifecycleScope.launch(Dispatchers.Main) {
//                Toast.makeText(
//                    this@AdminDashboardActivity,
//                    "Total Size $size",
//                    Toast.LENGTH_LONG
//                ).show()
//
//                val adapter = ItemListAdapter(itemList, itemDao = AppDatabase.getInstance(applicationContext).itemDao() )
//                recyclerView.layoutManager = LinearLayoutManager(this@AdminDashboardActivity)
//                recyclerView.adapter = adapter
//            }
//        }
    }

}