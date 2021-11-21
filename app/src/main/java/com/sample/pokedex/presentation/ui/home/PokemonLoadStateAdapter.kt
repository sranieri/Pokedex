package com.sample.pokedex.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.pokedex.databinding.PokedexStateItemBinding

class PokemonLoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PokemonLoadingStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            PokedexStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class LoadStateViewHolder(val binding: PokedexStateItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState, retry: () -> Unit){
            when (loadState) {
                is LoadState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.errorMsg.isVisible = false
                    binding.retryBtn.isVisible = false
                }
                is LoadState.Error -> {
                    binding.errorMsg.text = loadState.error.localizedMessage
                    binding.errorMsg.isVisible = true
                    binding.retryBtn.isVisible = true
                    binding.progressBar.isVisible = false
                }
                else -> {
                    binding.progressBar.isVisible = false
                }
            }


            binding.retryBtn.setOnClickListener {
                retry.invoke()
            }
        }
    }
}