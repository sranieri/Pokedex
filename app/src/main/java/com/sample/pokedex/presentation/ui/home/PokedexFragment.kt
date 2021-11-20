package com.sample.pokedex.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.pokedex.PokedexActivity
import com.sample.pokedex.databinding.FragmentPokedexBinding
import com.sample.pokedex.presentation.ui.detail.DetailActivity
import com.sample.pokedex.presentation.ui.detail.PokemonDetailFragment
import com.sample.pokedex.presentation.ui.dialog.TitleDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class PokedexFragment : Fragment() {

    private var viewBinding: FragmentPokedexBinding? = null
    private val binding get() = viewBinding!!

    private val viewModel: PokedexViewModel by viewModels()

    private var adapterList = PokemonAdapter()

    val listener: (CombinedLoadStates) -> Unit = { state ->
        if (adapterList.snapshot().isEmpty())
            viewModel.checkListState(state = state.source.refresh)
        else {
            binding.loading.isVisible = false
            binding.pokemonRecycler.isVisible = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentPokedexBinding.inflate(inflater, container, false)

        return binding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        binding.pokemonRecycler.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            this.adapter = adapterList.apply {
                onItemSelected = { entity ->
                    val intent =
                        Intent(
                            this@PokedexFragment.activity,
                            DetailActivity::class.java
                        ).apply {
                            putExtra(PokemonDetailFragment.ARG_POKEMON_ID, entity.id)
                            putExtra(PokemonDetailFragment.ARG_POKEMON_IMG, entity.imageUrl)
                        }
                    this@PokedexFragment.startActivity(intent)
                }
            }
        }

        viewModel.resetState()
    }

    override fun onResume() {
        super.onResume()
        adapterList.addLoadStateListener(listener)
    }

    override fun onPause() {
        super.onPause()
        adapterList.removeLoadStateListener(listener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun initObservers() {
        onStates(viewModel) {
            when (it) {
                is UIState.Loading -> {
                    binding.loading.isVisible = true
                    binding.pokemonRecycler.isVisible = false
                }
                is UIState.Failed -> {
                    binding.loading.isVisible = false
                    TitleDialogFragment.newInstance(
                        title = "Generic Error"
                    ).setPositiveButton("Retry") { dialog ->
                        viewModel.loadPokemonList()
                        dialog.dismiss()
                    }.setNegativeButton("Cancel") { dialog ->
                        dialog.dismiss()
                    }.showDialog(childFragmentManager)
                }
                is PokedexState.PokemonListState -> {
                    binding.loading.isVisible = false
                    binding.pokemonRecycler.isVisible = true
                    lifecycleScope.launch {
                        adapterList.submitData(it.list)
                    }
                }
            }
        }
    }

}