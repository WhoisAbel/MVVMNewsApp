package com.example.mvvmNewsApp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mvvmNewsApp.databinding.FragmentSearchNewsBinding
import com.example.mvvmNewsApp.ui.NewsActivity
import com.example.mvvmNewsApp.ui.NewsViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchNewsBinding
    lateinit var viewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSearchNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).newsViewModel
        Toast.makeText(requireContext(), "Search News", Toast.LENGTH_SHORT).show()

    }
}