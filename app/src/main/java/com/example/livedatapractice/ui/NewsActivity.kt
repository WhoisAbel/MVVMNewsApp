package com.example.livedatapractice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.livedatapractice.R
import com.example.livedatapractice.databinding.ActivityNewsBinding
import com.example.livedatapractice.db.ArticleDatabase
import com.example.livedatapractice.models.Article
import com.example.livedatapractice.repositories.NewsRepository

class NewsActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    private lateinit var binding: ActivityNewsBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelFactory = NewsViewModelFactory(repository)
        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]

        setupNavigation()
    }


    private fun setupNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }


}