package com.sample.pokedex.di.module

import com.sample.pokedex.presentation.ui.detail.DetailViewModel
import com.sample.pokedex.presentation.ui.home.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokedexViewModel(get())}
    viewModel { DetailViewModel(get()) }
}