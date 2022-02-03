package com.sample.pokedex.di.module

import com.sample.pokedex.data.datasource.CloudDataSource
import com.sample.pokedex.data.datasource.PokemonAbilityDao
import com.sample.pokedex.data.datasource.PokemonDao
import com.sample.pokedex.data.repository.CloudRepositoryImpl
import com.sample.pokedex.domain.repository.CloudRepository
import org.koin.dsl.module

val repoModule = module {
    fun provideCloudRepository(
        cloudDataSource: CloudDataSource,
        pokemonDao: PokemonDao,
        pokemonAbilityDao: PokemonAbilityDao
    ): CloudRepository {
        return CloudRepositoryImpl(cloudDataSource, pokemonDao, pokemonAbilityDao)
    }

    single {
        provideCloudRepository(get(), get(), get())
    }
}