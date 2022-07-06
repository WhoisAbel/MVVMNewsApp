package com.example.mvvmNewsApp.feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmNewsApp.feature.data.NewsRepository
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(private val repository: NewsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NewsViewModel(repository) as T
    }
}