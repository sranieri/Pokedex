package com.sample.pokedex.presentation.ui.home

import androidx.paging.PagingData
import com.sample.pokedex.domain.entity.PokemonEntity
import io.uniflow.core.flow.data.UIState

data class PokemonListState(val list: PagingData<PokemonEntity>) : UIState()
