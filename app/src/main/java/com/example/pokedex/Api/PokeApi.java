package com.example.pokedex.Api;

import com.example.pokedex.models.PokeAnswer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("pokemon")
    Call<PokeAnswer> getPokeList();
}
