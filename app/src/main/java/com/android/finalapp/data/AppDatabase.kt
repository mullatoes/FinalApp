package com.android.finalapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.finalapp.account.entities.Admin
import com.android.finalapp.account.entities.User
import com.android.finalapp.model.Item
import com.android.finalapp.model.Rating

@Database(
    entities = [Item::class, Rating::class, User::class, Admin::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    abstract fun accountDao(): AccountDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}