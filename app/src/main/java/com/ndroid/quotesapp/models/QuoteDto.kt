package com.ndroid.quotesapp.models

import kotlinx.serialization.Serializable


@Serializable
data class QuoteDto(
    val id: Int,
    val quote: String,
    val author: String
)