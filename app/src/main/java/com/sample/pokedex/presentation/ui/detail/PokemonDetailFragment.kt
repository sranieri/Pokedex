package com.sample.pokedex.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.pokedex.PokedexActivity
import com.sample.pokedex.R
import com.sample.pokedex.databinding.FragmentPokemonDetailBinding
import com.sample.pokedex.presentation.ui.dialog.TitleDialogFragment
import com.sample.pokedex.presentation.utils.getColorResByType
import com.sample.pokedex.presentation.utils.setColorByPokemon
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates
import io.uniflow.core.flow.data.UIState

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var viewBinding: FragmentPokemonDetailBinding? = null
    private val binding get() = viewBinding!!

    private val args: PokemonDetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        const val ARG_POKEMON_ID = "pokemon_id"
        const val ARG_POKEMON_IMG = "pokemon_img"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        onStates(viewModel) {
            when (it) {
                is PokemonState.PokemonInfo -> {
                    binding.loading.isVisible = false
                    with(it.pokemon){
                        val order = if(orderNumber < 1000){
                            String.format("#%03d", orderNumber)
                        } else {
                            "#$orderNumber"
                        }
                        binding.pokemonNumber.text = order
                        binding.pokemonName.text = name.replaceFirstChar { it.uppercase() }
                        binding.pokemonCard.setColorByPokemon(this)

                        getPokemonTypes().getOrNull(1)?.let { secondType ->
                            binding.secondTypeImg.apply {
                                setColorFilter(ContextCompat.getColor(context, getColorResByType(secondType)))
                                isVisible = true
                            }
                        }

                        binding.typesContainer.apply {
                            this@with.getPokemonTypes().forEach { type ->
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
                is UIState.Loading -> {
                    binding.loading.isVisible = true
                }
                is UIState.Failed -> {
                    binding.loading.isVisible = false
                    TitleDialogFragment.newInstance(
                        title = getString(R.string.generic_error)
                    ).setPositiveButton(getString(R.string.retry)) { dialog ->
                        viewModel.getPokemon(args.pokemonId)
                        dialog.dismiss()
                    }.setNegativeButton(getString(R.string.cancel)) { dialog ->
                        dialog.dismiss()
                        activity?.finish()
                    }.showDialog(childFragmentManager)
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { ctx ->
            Glide.with(ctx)
                .load(args.pokemonImg)
                .apply(RequestOptions.centerInsideTransform())
                .into(binding.pokemonImg)
        }

        viewModel.getPokemon(args.pokemonId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}