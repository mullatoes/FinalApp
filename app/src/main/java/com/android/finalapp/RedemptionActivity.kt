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
import com.android.finalapp.repository.ItemRepository
import com.android.finalapp.viewmodel.ItemViewModel
import com.android.finalapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RedemptionActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val repository = ItemRepository(AppDatabase.getInstance(applicationContext).itemDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ItemViewModel::class.java]

            loadItems()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_user)
        recyclerView.layoutManager = LinearLayoutManager(this@RedemptionActivity)
    }

    private fun loadItems() {
        lifecycleScope.launch(Dispatchers.IO) {
            val items = viewModel.getAllItems()
            lifecycleScope.launch(Dispatchers.Main) {
                initRecyclerView()
                recyclerView.adapter = ItemListAdapter(items, AppDatabase.getInstance(applicationContext).itemDao())
            }
        }
    }
}