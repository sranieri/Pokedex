package com.sample.pokedex.data.repository

import com.sample.pokedex.data.datasource.CloudDataSource
import com.sample.pokedex.data.datasource.PokemonAbilityDao
import com.sample.pokedex.data.datasource.PokemonDao
import com.sample.pokedex.domain.entity.AbilityEntity
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.entity.PokemonListEntity
import com.sample.pokedex.domain.repository.CloudRepository
import com.sample.pokedex.presentation.model.Pokemon
import com.sample.pokedex.presentation.model.PokemonAbility
import javax.inject.Inject

class CloudRepositoryImpl @Inject constructor(
    val cloudDataSource: CloudDataSource,
    val pokemonDao: PokemonDao,
    val pokemonAbilityDao: PokemonAbilityDao
) : CloudRepository {
    override suspend fun getPokemon(id: Int): PokemonEntity {
        return cloudDataSource.getPokemon(id)
    }

    override suspend fun getAbility(id: Int): AbilityEntity {
        return cloudDataSource.getAbility(id)
    }

    override suspend fun getPokemonFromDb(id: Int): PokemonEntity? {
        return pokemonDao.getPokemonById(id)?.let {
            val abilities = it.abilities.mapNotNull { id ->
                pokemonAbilityDao.getAbility(id)?.let { ability ->
                    AbilityEntity(
                        ability.id,
                        ability.name,
                        ability.effect,
                        ability.generation
                    )
                }
            }
            PokemonEntity(
                it.id,
                it.orderNumber,
                abilities,
                it.types,
                it.imageUrl,
                it.name,
                it.height,
                it.weight
            )
        }
    }

    override suspend fun getPokemonsFromDb(offset: Int): List<PokemonEntity> {
        return pokemonDao.getPokemons(offset).map {
            val abilities = it.abilities.map { id ->
                AbilityEntity(
                    id,
                    "",
                    "",
                    ""
                )
            }
            PokemonEntity(
                it.id,
                it.orderNumber,
                abilities,
                it.types,
                it.imageUrl,
                it.name,
                it.height,
                it.weight
            )
        }
    }

    override suspend fun getPokemons(offset: Int): List<PokemonListEntity> {
        return cloudDataSource.getPokemons(offset, ITEMS_FOR_PAGE)
    }

    override suspend fun getPokemonFromUrl(url: String): PokemonEntity {
        return cloudDataSource.getPokemonFromUrl(url)
    }

    override suspend fun storeAllPokemons(list: List<PokemonEntity>) {
        pokemonDao.insertAll(list.map {
            Pokemon(
                it.id,
                it.orderNumber,
                it.abilities.map { it.id },
                it.types,
                it.imageUrl,
                it.name,
                it.height,
                it.weight
            )
        })
        list.forEach {
            val abilities = it.abilities.map { ability ->
                PokemonAbility(
                    ability.id,
                    ability.name,
                    ability.effect,
                    ability.generation
                )
            }
            pokemonAbilityDao.insertAll(abilities)
        }
    }

    companion object {
        const val ITEMS_FOR_PAGE = 30
    }
}