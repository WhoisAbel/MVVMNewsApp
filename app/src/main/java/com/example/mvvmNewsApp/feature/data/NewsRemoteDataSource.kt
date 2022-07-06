package com.example.mvvmNewsApp.feature.data

import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val service: NewsService
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        service.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearch(searchQuery: String, pageNumber: Int) =
        service.searchForNews(searchQuery, pageNumber)
}