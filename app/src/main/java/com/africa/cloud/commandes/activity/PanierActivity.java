package com.africa.cloud.commandes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.africa.cloud.commandes.R;
import com.africa.cloud.commandes.adapter.PanierAdapter;
import com.africa.cloud.commandes.helper.MesHelpers;
import com.africa.cloud.commandes.model.Carte;
import com.africa.cloud.commandes.model.Lunette;
import com.africa.cloud.commandes.model.LunetteAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class PanierActivity extends AppCompatActivity {


    Realm realm;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    LunetteAdapter lunetteAdapter;
    Carte carte;
    ArrayAdapter adapter;
    private List<Carte> carteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        realm.init(this); //initialisation du realm



        //configuration du realm qui donne comme nom  de fichier par defaut ("default.realm") nous allons donner un nom et une version a notre realm
        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .name("panier.db") //affectation de nom creer par Realm pour stocké les données
                        .schemaVersion(1) //creer une version
                        .deleteRealmIfMigrationNeeded()
                        .build();
        realm = Realm.getInstance(config);








        recyclerView = (RecyclerView)findViewById(R.id.panier_recycler_view);

        //appel de la methode ajouter au panier en lui passant en  paramètre la liste des lunettes
       // mAdapter = new PanierAdapter(carteList,getApplicationContext());

        MesHelpers helpers = new MesHelpers(realm);

        helpers.reccuperation();
        carteList = helpers.reccuperation();


        mAdapter = new PanierAdapter(carteList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter( mAdapter);

    }








        //ici on met des setters pour pouvoir mettre le contenu de la liste
    //    carte.setPhoto(carte.getPhoto());
      //  carte.setTypes(carte.getTypes());
        //carte.setDescription(carte.getDescription());
        //carte.setPrix(carte.getPrix());

    }







