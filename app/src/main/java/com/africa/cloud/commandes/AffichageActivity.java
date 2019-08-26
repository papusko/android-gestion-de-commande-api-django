package com.africa.cloud.commandes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.africa.cloud.commandes.model.User;
import com.africa.cloud.commandes.model.UserAdapter;
import com.africa.cloud.commandes.service.GitHubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v7.widget.LinearLayoutManager.*;

public class AffichageActivity extends AppCompatActivity {

  ArrayList<String> user;
  ArrayAdapter adapter;
  UserAdapter userAdapter;
  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager layoutManager;
  private List<User> userList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_affichage);



    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.211:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GitHubClient user = retrofit.create(GitHubClient.class);
    Call<List<User>> call = user.reposForUser();

    call.enqueue(new Callback<List<User>>() {
      @Override
      public void onResponse(Call<List<User>> call, Response<List<User>> response) {


        List<User> repos = response.body();
        mAdapter = new UserAdapter(userList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext()); // pour avoir une seule ligne et une liste de defilement RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


      }

      @Override
      public void onFailure(Call<List<User>> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "Erreur lors de la reccupération des données", Toast.LENGTH_SHORT).show();
      }
    });



  }
}