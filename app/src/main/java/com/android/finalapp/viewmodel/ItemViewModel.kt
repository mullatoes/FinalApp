package com.android.finalapp.viewmodel

import androidx.lifecycle.ViewModel
import com.android.finalapp.model.Item
import com.android.finalapp.repository.ItemRepository

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    suspend fun getAllItems(): List<Item> {
        return itemRepository.getAllItems()
    }
}