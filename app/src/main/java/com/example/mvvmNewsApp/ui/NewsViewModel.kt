package com.example.mvvmNewsApp.ui

import androidx.lifecycle.ViewModel
import com.example.mvvmNewsApp.repositories.NewsRepository

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
}