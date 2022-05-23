package com.example.livedatapractice.data

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)