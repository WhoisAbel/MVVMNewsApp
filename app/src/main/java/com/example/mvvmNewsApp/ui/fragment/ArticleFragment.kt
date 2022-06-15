package com.example.mvvmNewsApp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mvvmNewsApp.databinding.FragmentArticleBinding
import com.example.mvvmNewsApp.ui.NewsActivity
import com.example.mvvmNewsApp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).newsViewModel

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