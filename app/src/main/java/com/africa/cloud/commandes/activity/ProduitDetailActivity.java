package com.africa.cloud.commandes.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.africa.cloud.commandes.LunetteFragment;
import com.africa.cloud.commandes.R;
import com.africa.cloud.commandes.model.Lunette;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProduitDetailActivity extends AppCompatActivity implements LunetteFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_detail);

        TextView descrip = (TextView)findViewById(R.id.lunette_item_description);
        TextView type = (TextView)findViewById(R.id.lunette_item_nom);
        ImageView image = (ImageView)findViewById(R.id.lunette_item_image);
        TextView prix = (TextView) findViewById(R.id.lunette_item_prix);




        //*******************************************************************************************************
        //Reccuperation du contenu envoyé par l'adapter
        //********************************************************************************************************
        Intent detail = getIntent(); //reccuperation de l'Intent (intention)
        String imag = detail.getStringExtra("image"); //reccuperation de l'image
        String description = detail.getStringExtra("description"); // reccuperation de la description
        String types = detail.getStringExtra("types"); // reccupération des types
        int pri = Integer.parseInt(detail.getStringExtra("prix")); // reccupération du prix


        Glide.with(getApplicationContext()).load(imag).into(image); //l'affichage de l'image dans l'ImageView definit dans notre fragment
        descrip.setText(description); //Affichage du contenu de la description  reccuperé dans le TextView
        type.setText(types); //affichage du types
        prix.setText(Integer.toString(pri)+" F-CFA"); //Affichage du prix



    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
