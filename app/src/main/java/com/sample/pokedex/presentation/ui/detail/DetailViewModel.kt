package com.sample.pokedex.presentation.ui.detail

import androidx.lifecycle.viewModelScope
import com.sample.pokedex.domain.usecase.FetchPokemonUseCase
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.launch

class DetailViewModel(
    private val fetchPokemonUseCase: FetchPokemonUseCase
): AndroidDataFlow() {

    init {
        action { setState(UIState.Empty) }
    }

    fun getPokemon(id: Int){
        viewModelScope.launch {
            runCatching {
                setState(UIState.Loading)
                val pokemon = fetchPokemonUseCase.execute(FetchPokemonUseCase.Params(id))
                setState(PokemonInfo(pokemon))
            }.getOrElse {
                setState(UIState.Failed())
            }
        }
    }
}