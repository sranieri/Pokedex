package com.sample.pokedex.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.pokedex.databinding.ViewholderPokemonBinding
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.presentation.utils.setColorByPokemon
import java.util.*

class PokemonAdapter : PagingDataAdapter<PokemonEntity, PokemonAdapter.ViewHolder>(DiffCallback()) {
    var onItemSelected: ((PokemonEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onItemSelected)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<PokemonEntity>() {
        override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(
        val binding: ViewholderPokemonBinding,
        val onItemSelected: ((PokemonEntity) -> Unit)? = null
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: PokemonEntity) {
            with(binding) {
                pokemonCard.setColorByPokemon(entity)
                pokemonName.text = entity.name.replaceFirstChar { it.uppercase() }
                Glide.with(root.context)
                    .load(entity.imageUrl)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(binding.pokemonImg)

                root.setOnClickListener {
                    onItemSelected?.invoke(entity)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, onItemSelected: ((PokemonEntity) -> Unit)? = null): ViewHolder =
                ViewHolder(
                    ViewholderPokemonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onItemSelected
                )
        }
    }
}