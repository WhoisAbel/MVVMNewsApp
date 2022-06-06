package com.example.livedatapractice.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.livedatapractice.databinding.FragmentArticleBinding
import com.example.livedatapractice.ui.NewsActivity
import com.example.livedatapractice.ui.NewsViewModel

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    lateinit var viewModel : NewsViewModel

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
    }
}