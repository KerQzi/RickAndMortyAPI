package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.fragments.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.R
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    companion object {
        const val ARG_ONBOARD_POSITION = "onboard_position"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeContent()
    }

    private fun loadContent(imageUrl: String, title: String, description: String) {
        Glide.with(binding.ivImage).load(imageUrl).into(binding.ivImage)
        binding.tvDescriptionTitle.text = title
        binding.tvDescription.text = description
    }

    private fun initializeContent() {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> loadContent(
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                "Rick & Morty",
                "Explore the multiverse with Rick and Morty."
            )
            1 -> loadContent(
                "https://rickandmortyapi.com/api/character/avatar/18.jpeg",
                "Characters",
                "Discover iconic characters from the show."
            )
            2 -> loadContent(
                "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
                "Locations",
                "Travel to different dimensions and locations."
            )
            3 -> loadContent(
                "https://rickandmortyapi.com/api/character/avatar/11.jpeg",
                "Episodes",
                "Dive into episode details and relive the adventures."
            )
        }
    }

}