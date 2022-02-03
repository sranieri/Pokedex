package com.sample.pokedex.domain.usecase

import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.repository.CloudRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchPokemonUseCase(
    val repository: CloudRepository
) {

    data class Params(
        val id: Int
    )

    suspend fun execute(params: Params): PokemonEntity{
        return withContext(Dispatchers.IO){
            val local = runCatching {
                repository.getPokemonFromDb(params.id)
            }.getOrNull()

            local ?: run {
                val pokemon = repository.getPokemon(params.id)
                val abilities = pokemon.abilities.map {
                    repository.getAbility(it.id)
                }
                pokemon.copy(abilities = abilities)
            }
        }
    }
}