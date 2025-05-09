package com.ndroid.quotesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class QuoteModel(

    val author: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quote: String,
    val workType: String,
    val time:Long = System.currentTimeMillis()

)