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

import com.africa.cloud.commandes.model.Login;
import com.africa.cloud.commandes.model.Lunette;
import com.africa.cloud.commandes.model.User;
import com.africa.cloud.commandes.service.GitHubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mUsername, mPassword;
    Button mLogin, mRegisterPage;
    TextView maListe,mResponseTv, mClients,mLunette;
    String username,password;
    private  User users;

    private static final String TAG = "hm" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mLunette = (TextView) findViewById(R.id.lunette);
        mClients = (TextView) findViewById(R.id.clients);
        mResponseTv = (TextView) findViewById(R.id.tv_response);
        mRegisterPage = (Button) findViewById(R.id.redirection);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        //maListe = (TextView) findViewById(R.id.listes);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = mUsername.getText().toString();
                password= mPassword.getText().toString();

                if (username.isEmpty() | password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "les 2 champs sont obligatoires", Toast.LENGTH_SHORT).show();
                }else {

                    sendPost(username,password);

                }

            }
        });


        mRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this,InscriptionActivity.class);
                startActivity(register);
            }
        });

        mClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ClientAjouterActivity.class);
                startActivity(i);
            }
        });

        mLunette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LunetteActivity.class);
                startActivity(i);
            }
        });



    }

    private void sendPost(String username, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.109:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient user = retrofit.create(GitHubClient.class);

        user.refreshToken(username,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "Enregistré avec succès." + response.body().toString());
                }

                Toast.makeText(getApplicationContext(), response.body().getToken()+"", Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Echecs d'enregistrement revoir le code"+t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void showResponse(String token) {

        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void onClick(View v) {

    }
}
