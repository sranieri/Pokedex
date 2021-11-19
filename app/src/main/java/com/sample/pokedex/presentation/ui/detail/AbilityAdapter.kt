package com.sample.pokedex.presentation.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.pokedex.databinding.ViewholderAbilityBinding
import com.sample.pokedex.domain.entity.AbilityEntity

class AbilityAdapter(private val items: MutableList<AbilityEntity>): RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    fun addItems(items: List<AbilityEntity>){
        this.items.clear()
        this.items.addAll(items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(
        val binding: ViewholderAbilityBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: AbilityEntity) {
            with(binding) {
                abilityName.text = entity.name.replaceFirstChar { it.uppercase() }
                abilityGeneration.text = entity.generation.uppercase()
                abilityDescription.text = entity.effect.replaceFirstChar { it.uppercase() }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder =
                ViewHolder(
                    ViewholderAbilityBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }
    }
}