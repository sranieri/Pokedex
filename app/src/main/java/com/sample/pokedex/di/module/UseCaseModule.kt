package com.sample.pokedex.di.module

import com.sample.pokedex.domain.usecase.FetchPokemonUseCase
import com.sample.pokedex.domain.usecase.FetchPokemonsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { FetchPokemonsUseCase(get()) }
    factory { FetchPokemonUseCase(get()) }
}