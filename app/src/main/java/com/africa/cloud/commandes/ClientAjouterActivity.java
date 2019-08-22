package com.africa.cloud.commandes;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.africa.cloud.commandes.model.Clients;
import com.africa.cloud.commandes.model.User;
import com.africa.cloud.commandes.service.GitHubClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientAjouterActivity extends AppCompatActivity {

    private static final String TAG = "cool" ;
    private EditText  mNom, mPrenom, mAdresse,mTelephone;
    private Button mEnregistrer;
    String  nom, prenom, adresse,telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_ajouter);


        mPrenom = (EditText) findViewById(R.id.nom);
        mNom = (EditText) findViewById(R.id.prenom);
        mAdresse = (EditText) findViewById(R.id.adresse);
        mTelephone = (EditText) findViewById(R.id.telephone);
        mEnregistrer = (Button) findViewById(R.id.enregistrer);


        mEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nom = mPrenom.getText().toString();
                prenom = mNom.getText().toString();
                adresse = mAdresse.getText().toString();
                telephone = mTelephone.getText().toString();


                if (nom.isEmpty()| prenom.isEmpty()| adresse.isEmpty()| telephone.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }else {


                    sendPost(nom,prenom,adresse,telephone);


                }





            }
        });




    }


    private void sendPost(String nom, String prenom, String adresse, String telephone) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.211:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient user = retrofit.create(GitHubClient.class);

        user.saveClients(nom,prenom,adresse,telephone).enqueue(new Callback<Clients>() {
            @Override
            public void onResponse(Call<Clients> call, Response<Clients> response) {


                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "Enregistré avec succès." + response.body().toString());
                }

                Toast.makeText(getApplicationContext(), response.code()+"", Toast.LENGTH_SHORT).show();
            }

            private void showResponse(String toString) {


            }

            @Override
            public void onFailure(Call<Clients> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Echecs d'enregistrement revoir le code"+t, Toast.LENGTH_SHORT).show();

            }
        });

    }


}
