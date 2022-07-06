package com.example.mvvmNewsApp.core.di

import com.example.mvvmNewsApp.feature.ui.NewsActivity
import com.example.mvvmNewsApp.feature.ui.fragment.ArticleFragment
import com.example.mvvmNewsApp.feature.ui.fragment.BreakingNewsFragment
import com.example.mvvmNewsApp.feature.ui.fragment.SavedNewsFragment
import com.example.mvvmNewsApp.feature.ui.fragment.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NewsModule::class
    ]
)

interface AppComponent {
    fun inject(fragment: ArticleFragment)
    fun inject(fragment: BreakingNewsFragment)
    fun inject(fragment: SavedNewsFragment)
    fun inject(fragment: SearchFragment)

}