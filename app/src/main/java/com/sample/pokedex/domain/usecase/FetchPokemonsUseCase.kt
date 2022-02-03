package com.sample.pokedex.domain.usecase

import android.net.Uri
import com.sample.pokedex.domain.entity.PokemonEntity
import com.sample.pokedex.domain.repository.CloudRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class FetchPokemonsUseCase(
    val repository: CloudRepository
){

    data class Params(
        val offset: Int
    )

    suspend fun execute(params: Params): List<PokemonEntity>{
        return withContext(Dispatchers.IO){
            runCatching {
                val local = repository.getPokemonsFromDb(params.offset)
                if(local.isNotEmpty()){
                    local
                } else {
                    val remote = repository.getPokemons(params.offset).map {
                        async {
                            val id = Uri.parse(it.url).lastPathSegment?.replace("/", "")?.toInt() ?: -1
                            val localPokemon = runCatching {
                                repository.getPokemonFromDb(id)
                            }.getOrNull()
                            localPokemon ?: run {
                                val pokemon = repository.getPokemonFromUrl(it.url)
                                val abilities = pokemon.abilities.map {
                                    async {
                                        repository.getAbility(it.id)
                                    }
                                }.awaitAll()
                                pokemon.copy(abilities = abilities)
                            }
                        }
                    }.awaitAll()
                    repository.storeAllPokemons(remote)
                    remote
                }
            }.getOrElse {
                // TODO: add error handling
                listOf()
            }
        }
    }
}