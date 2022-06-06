package com.example.mvvmNewsApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmNewsApp.R
import com.example.mvvmNewsApp.databinding.ActivityNewsBinding
import com.example.mvvmNewsApp.db.ArticleDatabase
import com.example.mvvmNewsApp.repositories.NewsRepository

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