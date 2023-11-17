package com.example.picobotellaequipo6.webservice

import com.example.picobotellaequipo6.model.Pokemon
import com.example.picobotellaequipo6.utils.Constants.END_POINT
import retrofit2.http.GET

interface ApiService {
    data class PokemonResponse(
        val pokemon: MutableList<Pokemon>
    )

    @GET(END_POINT)
    suspend fun getPokemons(): PokemonResponse
}