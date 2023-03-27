package com.android.finalapp.repository

import com.android.finalapp.data.ItemDao
import com.android.finalapp.model.Item

class ItemRepository(
    private val itemDao: ItemDao
) {
    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

}