package com.android.finalapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "ratings",
    foreignKeys =
    [ForeignKey(
        entity = Item::class, parentColumns = ["id"],
        childColumns = ["itemId"], onDelete = CASCADE
    )]
)
data class Rating(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemId: Int,
    val ratingValue: Int,
    val comment: String
)
