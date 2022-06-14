package com.example.mvvmNewsApp.repositories

import com.example.mvvmNewsApp.api.RetrofitInstance
import com.example.mvvmNewsApp.db.ArticleDatabase
import com.example.mvvmNewsApp.models.NewsResponse
import retrofit2.Response

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int)=
         RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

}