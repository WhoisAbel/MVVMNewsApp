package com.example.mvvmNewsApp.feature.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmNewsApp.R
import com.example.mvvmNewsApp.core.di.AppModule
import com.example.mvvmNewsApp.core.di.DaggerAppComponent
import com.example.mvvmNewsApp.feature.ui.adapters.NewsAdapter
import com.example.mvvmNewsApp.databinding.FragmentSearchNewsBinding
import com.example.mvvmNewsApp.feature.ui.NewsActivity
import com.example.mvvmNewsApp.feature.ui.NewsViewModel
import com.example.mvvmNewsApp.core.util.Resource
import com.example.mvvmNewsApp.feature.ui.NewsViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchNewsBinding

    private val viewModel: NewsViewModel by activityViewModels{viewModelFactory}

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory
    lateinit var searchAdapter: NewsAdapter
    private val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
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

        setupRecyclerView()

        var job: Job? = null
        binding.etSearch.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.getSearchNews(it.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        searchAdapter.differ.submitList(it.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.e(TAG, "An error occured: $it")

                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }


    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        searchAdapter = NewsAdapter {
            val bundle = Bundle().apply {
                putParcelable("article", it)
            }

            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }
        binding.rvSearchNews.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}