package com.africa.cloud.commandes.model;

import com.africa.cloud.commandes.service.GitHubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {


   // Lunette lunette = new Lunette(types, photo);



    private List<Lunette> prepareMovieData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.211:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient user = retrofit.create(GitHubClient.class);

        user.getLunette().enqueue(new Callback<List<Lunette>>() {
            @Override
            public void onResponse(Call<List<Lunette>> call, Response<List<Lunette>> response) {

                if(response.isSuccessful()){
                    List<Lunette>  lunetteList = new ArrayList<>();

                    for (Lunette l : lunetteList )
                    {
                        lunetteList.add(l);
                        System.out.println(lunetteList);

                    }
                }

            }

            @Override
            public void onFailure(Call<List<Lunette>> call, Throwable t) {


            }
        });


        return null;
    }


}
