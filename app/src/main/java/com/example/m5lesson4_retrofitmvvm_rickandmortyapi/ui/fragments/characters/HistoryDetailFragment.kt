package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil3.load
import com.bumptech.glide.Glide
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterEntity
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.databinding.FragmentHistoryDetailBinding
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentHistoryDetailBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val args: HistoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding){
        val character = args.character as CharacterEntity
        val bitmap = ImageUtils.base64ToBitmap(character.imageBase64 ?: "")
        name.text = character.name
        status.text = character.status
        gender.text = character.gender
        location.text = character.location
        species.text = character.species
        firstSeen.text = character.firstEpisodeName
        origin.text = character.origin
        image.load(bitmap)
    }
}