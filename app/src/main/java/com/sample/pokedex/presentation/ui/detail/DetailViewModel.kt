package com.sample.pokedex.presentation.ui.detail

import androidx.lifecycle.viewModelScope
import com.sample.pokedex.domain.repository.CloudRepository
import com.sample.pokedex.domain.usecase.FetchPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val cloudRepository: CloudRepository,
    private val fetchPokemonUseCase: FetchPokemonUseCase
): AndroidDataFlow() {

    init {
        action { setState(UIState.Empty) }
    }

    fun getPokemon(id: Int){
        viewModelScope.launch {
            runCatching {
                val currentState = getState()
                val pokemon = fetchPokemonUseCase.execute(FetchPokemonUseCase.Params(id))
                val state = when(currentState){
                    is PokemonState.PokemonInfo -> {
                        currentState.copy(pokemon = pokemon)
                    }
                    else -> PokemonState.PokemonInfo(pokemon)
                }
                setState(state)
            }.getOrElse {
            }
        }
    }
}