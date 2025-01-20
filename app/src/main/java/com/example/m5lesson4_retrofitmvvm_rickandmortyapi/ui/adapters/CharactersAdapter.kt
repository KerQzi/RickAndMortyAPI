package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.CharacterStatus
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.databinding.CharacterItemBinding
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.viewmodels.CharactersViewModel

class CharactersAdapter: ListAdapter<Character, CharactersAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel by lazy { CharactersViewModel() }
        fun bind(item: Character?) {
            binding.apply {
                name.text = item?.name
                status.text = item?.status
                species.text = item?.species
                location.text = item?.location?.name ?: "???"
                firstSeen.text = item?.episode?.get(0) ?: "???"
                Glide.with(image).load(item?.image).into(this.image)

                val characterStatus = when (item?.status) {
                    "Alive" -> CharacterStatus.ALIVE
                    "Dead" -> CharacterStatus.DEAD
                    else -> CharacterStatus.UNKNOWN
                }
                statusIcon.imageTintList = statusIcon.context.getColorStateList(characterStatus.colorResId)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    class DiffCallback: DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
}