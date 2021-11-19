package com.sample.pokedex.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.pokedex.databinding.FragmentPokedexBinding
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokedexFragment : Fragment() {

    private var viewBinding: FragmentPokedexBinding? = null
    private val binding get() = viewBinding!!

    private val viewModel: PokedexViewModel by viewModels()

    private var adapterList = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                onItemSelected = { id ->
                    findNavController().navigate(
                        PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(id)
                    )
                }
            }
        }

        viewModel.resetState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun initObservers() {
        onStates(viewModel) {
            if(it is PokedexState.PokemonListState){
                lifecycleScope.launch {
                    adapterList.submitData(it.list)
                }
            }
        }

        onEvents(viewModel) {
            if (it is PokedexEvent) {
                when (it) {
                    is PokedexEvent.GenericErrorEvent -> {
                        Toast.makeText(context, "Generic error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}