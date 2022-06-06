package com.example.mvvmNewsApp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmNewsApp.repositories.NewsRepository

class NewsViewModelFactory(private val repository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}