package com.example.mvvmNewsApp.core.di

import com.example.mvvmNewsApp.feature.data.NewsService
import com.example.mvvmNewsApp.core.db.ArticleDao
import com.example.mvvmNewsApp.core.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NewsModule {

    @Singleton
    @Provides
    fun provideBankService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun providesNewsDao(db: ArticleDatabase): ArticleDao {
        return db.articleDao()
    }
}