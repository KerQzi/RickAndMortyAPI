package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.fragments.characters

import HistoryAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.databinding.FragmentHistoryBinding
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.interfaces.OnItemClick
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterEntity
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter by lazy { HistoryAdapter(viewModel, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.getViewedCharacters().observe(viewLifecycleOwner) { viewedCharacters ->
            adapter.submitList(viewedCharacters)
        }
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = this@HistoryFragment.adapter
    }

    override fun onItemClick(character: CharacterEntity) {
        val action = HistoryFragmentDirections.actionViewedCharactersFragmentToHistoryDetailFragment(character)
        findNavController().navigate(action)
    }
}