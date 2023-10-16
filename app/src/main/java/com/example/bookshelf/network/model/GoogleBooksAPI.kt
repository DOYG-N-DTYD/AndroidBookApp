package com.example.bookshelf.network.model

data class GoogleBooksAPI(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)