package com.example.mvvmNewsApp.feature.data

import com.example.mvvmNewsApp.feature.data.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        remoteDataSource.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearch(searchQuery: String, pageNumber: Int) =
        remoteDataSource.getSearch(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = localDataSource.upsert(article)

    suspend fun deleteArticle(article: Article) = localDataSource.deleteArticle(article)

    fun getAllArticles() = localDataSource.getAllArticles()

}