package com.sample.pokedex.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.pokedex.databinding.FragmentPokemonDetailBinding
import com.sample.pokedex.presentation.utils.getColorResByType
import com.sample.pokedex.presentation.utils.getTypeDrawable
import com.sample.pokedex.presentation.utils.setColorByPokemon
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var viewBinding: FragmentPokemonDetailBinding? = null
    private val binding get() = viewBinding!!

    private val args: PokemonDetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        onStates(viewModel) {
            if (it is PokemonState.PokemonInfo) {
                with(it.pokemon){
                    context?.let { ctx ->
                        Glide.with(ctx)
                            .load(imageUrl)
                            .apply(RequestOptions.centerInsideTransform())
                            .into(binding.pokemonImg)
                    }
                    val order = if(orderNumber < 1000){
                        String.format("#%03d", orderNumber)
                    } else {
                        "#$orderNumber"
                    }
                    binding.pokemonNumber.text = order
                    binding.pokemonName.text = name.replaceFirstChar { it.uppercase() }
                    binding.pokemonCard.setColorByPokemon(this)

                    types.getOrNull(1)?.let { secondType ->
                        binding.secondTypeImg.apply {
                            setColorFilter(ContextCompat.getColor(context, getColorResByType(secondType)))
                            isVisible = true
                        }
                    }

                    binding.typesContainer.apply {
                        this@with.types.forEach { type ->
                            addView(
                                PokemonTypeView(context).apply {
                                    updateColors(type)
                                }
                            )
                        }
                    }

                    binding.abilitiesRecycler.apply {
                        isNestedScrollingEnabled = false
                        setHasFixedSize(true)
                        adapter = AbilityAdapter(abilities.toMutableList())
                    }
                }

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPokemon(args.pokemonId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}