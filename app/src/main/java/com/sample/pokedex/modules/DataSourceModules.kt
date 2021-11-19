package com.sample.pokedex.modules

import android.content.Context
import androidx.room.Room
import com.sample.pokedex.data.datasource.CloudDataSource
import com.sample.pokedex.data.datasource.PokemonAbilityDao
import com.sample.pokedex.data.datasource.PokemonDao
import com.sample.pokedex.presentation.api.CloudApi
import com.sample.pokedex.presentation.api.mapper.PokemonEntityMapper
import com.sample.pokedex.presentation.api.mapper.PokemonListEntityMapper
import com.sample.pokedex.presentation.datasource.AppDB
import com.sample.pokedex.presentation.datasource.CloudDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModules {
    @InstallIn(SingletonComponent::class)
    @Module
    class DatabaseModule {
        @Provides
        fun providePokemonDao(appDatabase: AppDB): PokemonDao {
            return appDatabase.pokemonDao()
        }

        @Provides
        fun providePokemonAbilityDao(appDatabase: AppDB): PokemonAbilityDao {
            return appDatabase.pokemonAbilityDao()
        }

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): AppDB {
            return Room.databaseBuilder(appContext, AppDB::class.java, "pokemons")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    @Provides
    fun provideCloudDataSource(
        api: CloudApi,
        pokemonEntityMapper: PokemonEntityMapper,
        pokemonListEntityMapper: PokemonListEntityMapper
    ): CloudDataSource {
        return CloudDataSourceImpl(api, pokemonEntityMapper, pokemonListEntityMapper)
    }
}