package com.example.mvvmNewsApp.core.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmNewsApp.feature.data.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>
}