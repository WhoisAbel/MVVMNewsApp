package com.example.mvvmNewsApp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmNewsApp.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>
}