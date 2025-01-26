package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.R
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.databinding.FragmentCharactersBinding
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.adapters.CharactersAdapter
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter by lazy {
        CharactersAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getCharacters()

        viewModel.charactersData.observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }
    }
     private fun setupRecyclerView() = with(binding.recyclerView) {
         layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
         adapter = this@CharactersFragment.adapter
     }
}