package com.android.finalapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.finalapp.model.Item

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<Item>
}