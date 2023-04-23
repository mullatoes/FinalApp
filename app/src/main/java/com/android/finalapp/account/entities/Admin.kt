package com.android.finalapp.account.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admins")
data class Admin(
    @PrimaryKey val email: String,
    val password: String
)
