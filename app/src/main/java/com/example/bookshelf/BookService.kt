package com.example.bookshelf

import com.example.bookshelf.network.model.GoogleBooksAPI
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int // maxResults from google api, max val = 40 // TODO пагинация
    ): GoogleBooksAPI
}