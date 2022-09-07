package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.pokedex.Api.PokeApi;
import com.example.pokedex.models.PokeAnswer;
import com.example.pokedex.models.Pokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData();
    }

    public void getData(){
        PokeApi service = retrofit.create(PokeApi.class);
        Call<PokeAnswer> pokeAnswerCall = service.getPokeList();

        pokeAnswerCall.enqueue(new Callback<PokeAnswer>() {
            @Override
            public void onResponse(Call<PokeAnswer> call, Response<PokeAnswer> response) {
                if (response.isSuccessful()){
                    PokeAnswer pokeAnswer = response.body();
                    ArrayList <Pokemon> pokeList = pokeAnswer.getResults();
                    for (int i = 0; i < pokeList.size(); i++) {
                        Pokemon p = pokeList.get(i);
                        Log.i(TAG, " Pokemon: " + p.getName());
                    }
                } else {
                    Log.e(TAG, " onResponse " + response.body());
                }
            }

            @Override
            public void onFailure(Call<PokeAnswer> call, Throwable t) {
                Log.e(TAG, " onFailure " + t.getMessage());

            }
        });
    }
}