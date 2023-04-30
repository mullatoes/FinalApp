package com.android.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.finalapp.adapter.ItemListAdapter
import com.android.finalapp.data.AppDatabase
import com.android.finalapp.model.Item
import com.android.finalapp.repository.ItemRepository
import com.android.finalapp.viewmodel.ItemViewModel
import com.android.finalapp.viewmodel.ViewModelFactory
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RedemptionActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ItemViewModel
    private lateinit var itemList: MutableList<Item>
    private lateinit var itemsCollection: CollectionReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val db = Firebase.firestore
        itemsCollection = db.collection("items")

        itemList = mutableListOf()


//        val repository = ItemRepository(AppDatabase.getInstance(applicationContext).itemDao())
//        val viewModelFactory = ViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory)[ItemViewModel::class.java]
//
           loadItems()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_user)
        recyclerView.layoutManager = LinearLayoutManager(this@RedemptionActivity)
    }

    private fun loadItems() {
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
            initRecyclerView()
            recyclerView.adapter = adapter
        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            val items = viewModel.getAllItems()
//            lifecycleScope.launch(Dispatchers.Main) {
//                initRecyclerView()
//                recyclerView.adapter = ItemListAdapter(items, AppDatabase.getInstance(applicationContext).itemDao())
//            }
//        }
    }
}