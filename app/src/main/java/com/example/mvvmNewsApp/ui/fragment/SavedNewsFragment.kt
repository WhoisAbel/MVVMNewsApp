package com.example.mvvmNewsApp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmNewsApp.R
import com.example.mvvmNewsApp.adapters.NewsAdapter
import com.example.mvvmNewsApp.databinding.FragmentSavedNewsBinding
import com.example.mvvmNewsApp.ui.NewsActivity
import com.example.mvvmNewsApp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    lateinit var viewModel: NewsViewModel
    lateinit var saveAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).newsViewModel
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