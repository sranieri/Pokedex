package com.sample.pokedex.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.sample.pokedex.R
import com.sample.pokedex.databinding.ViewholderPokemonBinding
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.presentation.utils.setColorByPokemon
import java.util.*

class PokemonAdapter(val onItemSelected: ((PokemonEntity) -> Unit)) : PagingDataAdapter<PokemonEntity, PokemonAdapter.ViewHolder>(DiffCallback()) {

    companion object{
        const val LOADING_VIEW_TYPE = 0
        const val POKEMON_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            LOADING_VIEW_TYPE
        } else {
            POKEMON_TYPE
        }
    }

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
        val onItemSelected: ((PokemonEntity) -> Unit)
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: PokemonEntity) {
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            with(binding) {
                pokemonCard.setColorByPokemon(entity)
                pokemonName.text = entity.name.replaceFirstChar { it.uppercase() }
                Glide.with(root.context)
                    .load(entity.imageUrl)
                    .apply(RequestOptions.centerInsideTransform())
                    .transition(DrawableTransitionOptions.withCrossFade(factory))
                    .placeholder(R.drawable.ditto_placeholder)
                    .into(binding.pokemonImg)

                root.setOnClickListener {
                    onItemSelected.invoke(entity)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, onItemSelected: ((PokemonEntity) -> Unit)): ViewHolder =
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