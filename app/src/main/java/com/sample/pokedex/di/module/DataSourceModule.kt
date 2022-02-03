package com.sample.pokedex.di.module

import android.app.Application
import androidx.room.Room
import com.sample.pokedex.data.datasource.CloudDataSource
import com.sample.pokedex.data.datasource.PokemonAbilityDao
import com.sample.pokedex.data.datasource.PokemonDao
import com.sample.pokedex.presentation.api.CloudApi
import com.sample.pokedex.presentation.api.mapper.PokemonEntityMapper
import com.sample.pokedex.presentation.api.mapper.PokemonListEntityMapper
import com.sample.pokedex.presentation.datasource.AppDB
import com.sample.pokedex.presentation.datasource.CloudDataSourceImpl

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {

    fun providePokemonDao(appDatabase: AppDB): PokemonDao {
        return appDatabase.pokemonDao()
    }

    fun providePokemonAbilityDao(appDatabase: AppDB): PokemonAbilityDao {
        return appDatabase.pokemonAbilityDao()
    }

    fun provideAppDatabase(application: Application): AppDB {
        return Room.databaseBuilder(application, AppDB::class.java, "pokemons")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCloudDataSource(
        api: CloudApi
    ): CloudDataSource {
        return CloudDataSourceImpl(api, PokemonEntityMapper(), PokemonListEntityMapper())
    }

    single { provideAppDatabase(androidApplication()) }
    single { providePokemonDao(get()) }
    single { providePokemonAbilityDao(get()) }
    single { provideCloudDataSource(get()) }
}

