package com.example.mvvmNewsApp.feature.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmNewsApp.R
import com.example.mvvmNewsApp.core.di.AppModule
import com.example.mvvmNewsApp.core.di.DaggerAppComponent
import com.example.mvvmNewsApp.feature.ui.adapters.NewsAdapter
import com.example.mvvmNewsApp.databinding.FragmentSavedNewsBinding
import com.example.mvvmNewsApp.feature.ui.NewsActivity
import com.example.mvvmNewsApp.feature.ui.NewsViewModel
import com.example.mvvmNewsApp.feature.ui.NewsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    private val viewModel: NewsViewModel by activityViewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory
    lateinit var saveAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
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

    }

    private fun setupRecyclerView() {
        saveAdapter = NewsAdapter {

            val bundle = Bundle().apply {
                putParcelable("article", it)
            }

            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.getAllArticles().observe(viewLifecycleOwner, Observer {
            saveAdapter.differ.submitList(it)
        })

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = saveAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(requireView(), "Successfully deleted", Snackbar.LENGTH_LONG).also {
                    it.setAction("Undo") {
                        viewModel.upsert(article)
                    }
                }.show()
            }
        }

        val touchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        touchHelper.attachToRecyclerView(binding.rvSavedNews)

        binding.rvSavedNews.apply {
            adapter = saveAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}