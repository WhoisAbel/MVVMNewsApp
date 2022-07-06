package com.example.mvvmNewsApp.feature.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmNewsApp.R
import com.example.mvvmNewsApp.databinding.ActivityNewsBinding
import com.example.mvvmNewsApp.core.db.ArticleDatabase
import com.example.mvvmNewsApp.core.di.AppModule
import com.example.mvvmNewsApp.core.di.DaggerAppComponent
import com.example.mvvmNewsApp.feature.data.NewsRepository
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }


    private fun setupNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }


}