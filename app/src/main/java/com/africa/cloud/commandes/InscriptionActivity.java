package com.africa.cloud.commandes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.commandes.model.User;
import com.africa.cloud.commandes.service.GitHubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "cool" ;
    private TextView mEntete, mInscriptionpage,mResponseTv;
    private EditText mPrenom, mNom, mUsername,mPassword,mConfirmPassword, mEmail;
    private Button mInscription;
    private  User users;

    String prenom, nom, username,email,password,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);




        mInscriptionpage = (TextView) findViewById(R.id.inscription);
        mPrenom = (EditText) findViewById(R.id.prenom);
        mNom = (EditText) findViewById(R.id.nom);
        mUsername = (EditText) findViewById(R.id.username);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mConfirmPassword = (EditText) findViewById(R.id.confirm_password);
        mInscription =(Button) findViewById(R.id.inscription);
        mResponseTv = (TextView) findViewById(R.id.tv_response);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.211:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient user = retrofit.create(GitHubClient.class);


        mInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prenom = mPrenom.getText().toString();
                nom = mNom.getText().toString();
                username = mUsername.getText().toString();
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                confirmPassword = mConfirmPassword.getText().toString();

                if (prenom.isEmpty()| nom.isEmpty()| username.isEmpty()| email.isEmpty()| password.isEmpty()|confirmPassword.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }else {


                    sendPost(prenom,nom,username,email,password,confirmPassword);


                }

            }
        });


    }

    private void sendPost(String prenom, String nom, String username, String email, String password, String confirmPassword) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.211:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient user = retrofit.create(GitHubClient.class);

        user.saveUser(prenom,nom,username,email,password,confirmPassword).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "Enregistré avec succès." + response.body().toString());
                }

                Toast.makeText(getApplicationContext(), response.code()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Echecs d'enregistrement revoir le code"+t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showResponse(String response) {


        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);

    }


    @Override
    public void onClick(View v) {

    }
}