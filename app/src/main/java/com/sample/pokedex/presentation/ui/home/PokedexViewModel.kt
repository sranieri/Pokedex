package com.sample.pokedex.presentation.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.sample.pokedex.domain.paging.PokedexPagingSource
import com.sample.pokedex.domain.usecase.FetchPokemonsUseCase
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.coroutines.onFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val fetchPokemonsUseCase: FetchPokemonsUseCase
) : AndroidDataFlow() {

    init {
        //set initial state
        viewModelScope.launch {
            val currentState = getState()
            if (currentState !is PokemonListState) {
                setState(UIState.Loading)
            }
        }
    }

    @ExperimentalPagingApi
    fun resetState(){
        viewModelScope.launch {
            val currentState = getState()
            if(currentState !is PokemonListState){
                loadPokemonList()
            }
        }
    }

    @ExperimentalPagingApi
    fun loadPokemonList() {
        val flow = Pager(
            PagingConfig(pageSize = 5)
        ) {
            PokedexPagingSource(fetchPokemonsUseCase)
        }.flow
            .cachedIn(viewModelScope)
        // Launch a job
        viewModelScope.launch {
            onFlow(
                flow = { flow },
                doAction = { value ->
                    setState(PokemonListState(list = value))
                }
            )
        }
    }

    fun checkListState(state: LoadState) {
        viewModelScope.launch {
            when (state) {
                is LoadState.Loading -> setState(UIState.Loading)
                is LoadState.Error -> setState(UIState.Failed())


                else -> {
                }
            }
        }
    }
}