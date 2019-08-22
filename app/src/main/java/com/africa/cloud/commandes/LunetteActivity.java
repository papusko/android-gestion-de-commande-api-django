package com.africa.cloud.commandes;

import android.app.AlertDialog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.commandes.model.Lunette;
import com.africa.cloud.commandes.model.LunetteAdapter;
import com.africa.cloud.commandes.service.GitHubClient;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;
import static java.util.Objects.*;

public class LunetteActivity extends AppCompatActivity {


    private static final String TAG ="cool" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    LunetteAdapter lunetteAdapter;
    ArrayAdapter adapter;
    private List<Lunette> lunetteList = new ArrayList<>();

    RequestQueue mRequestQueue;
    TextView resultat;
    ImageView lun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunette);

        lun = (ImageView) findViewById(R.id.lunette);
        recyclerView = (RecyclerView) findViewById(R.id.lunette_recycler_view);

        resultat = (TextView) findViewById(R.id.text_view_result);


        //setting up volley request queue
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();




        mAdapter = new LunetteAdapter(lunetteList,this);
       prepareLunetteData();
     //   parseJSON();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter( mAdapter);



    }

    //method to parse json data from url
   /* public void parseJSON(){

        String url = "http://192.168.1.211:8000/images/lunettes/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);


                        String types = hit.getString("types");
                        String photo = hit.getString("photo");

                        lunetteList.add(new Lunette(types, photo));

                    }
                    mAdapter = new LunetteAdapter(lunetteList, LunetteActivity.this);
                    recyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }*/


    private void prepareLunetteData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.211:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient user = retrofit.create(GitHubClient.class);

        user.getLunette().enqueue(new Callback<List<Lunette>>() {
            @Override
            public void onResponse(Call<List<Lunette>> call, Response<List<Lunette>> response) {

                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.code()+"", Toast.LENGTH_SHORT).show();
                    List<Lunette>  lunetteList = response.body();


                    for (Lunette l : lunetteList )
                    {

                        System.out.print(lunetteList);

                    }



                    mAdapter = new LunetteAdapter(lunetteList, LunetteActivity.this);
                    recyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Lunette>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "i djasigui kagna"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }



}
