package com.example.livedatapractice.ui

import androidx.lifecycle.ViewModel
import com.example.livedatapractice.repositories.NewsRepository

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
}