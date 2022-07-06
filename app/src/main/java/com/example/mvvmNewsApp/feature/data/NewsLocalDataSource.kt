package com.example.mvvmNewsApp.feature.data

import com.example.mvvmNewsApp.core.db.ArticleDao
import com.example.mvvmNewsApp.core.db.ArticleDatabase
import com.example.mvvmNewsApp.feature.data.models.Article
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    private val db: ArticleDatabase,
    private val dao: ArticleDao
){
    suspend fun upsert(article: Article) = dao.upsert(article)

    suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)

    fun getAllArticles() = dao.getAllArticles()
}