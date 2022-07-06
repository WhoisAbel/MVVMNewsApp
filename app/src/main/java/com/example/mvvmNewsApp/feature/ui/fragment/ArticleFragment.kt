package com.example.mvvmNewsApp.feature.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.mvvmNewsApp.core.di.AppModule
import com.example.mvvmNewsApp.core.di.DaggerAppComponent
import com.example.mvvmNewsApp.databinding.FragmentArticleBinding
import com.example.mvvmNewsApp.feature.ui.NewsActivity
import com.example.mvvmNewsApp.feature.ui.NewsViewModel
import com.example.mvvmNewsApp.feature.ui.NewsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private val viewModel: NewsViewModel by activityViewModels{viewModelFactory}
    private val args: ArticleFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .appModule(AppModule(context.applicationContext))
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            webViewClient = WebViewClient()
            args.article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener {
            viewModel.upsert(args.article)
            Snackbar.make(it, "Successfully Saved", Snackbar.LENGTH_LONG).show()
        }
    }
}