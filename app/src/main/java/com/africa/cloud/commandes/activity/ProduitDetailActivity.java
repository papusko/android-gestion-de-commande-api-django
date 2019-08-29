package com.africa.cloud.commandes.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.commandes.LunetteFragment;
import com.africa.cloud.commandes.R;
import com.africa.cloud.commandes.model.Lunette;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduitDetailActivity extends AppCompatActivity implements LunetteFragment.OnFragmentInteractionListener {
    private Lunette lunette;
    private List<Lunette> lunetteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_detail);

        final TextView descrip = (TextView)findViewById(R.id.lunette_item_description);
        TextView type = (TextView)findViewById(R.id.lunette_item_nom);
        ImageView image = (ImageView)findViewById(R.id.lunette_item_image);
        TextView prix = (TextView) findViewById(R.id.lunette_item_prix);
        Button ajout = (Button) findViewById(R.id.ajouter);
        lunette = new Lunette();

        lunetteList = new ArrayList<>();


        //*******************************************************************************************************
        //Reccuperation du contenu envoyé par l'adapter
        //********************************************************************************************************
        Intent detail = getIntent(); //reccuperation de l'Intent (intention)
        final String imag = detail.getStringExtra("image"); //reccuperation de l'image
        final String description = detail.getStringExtra("description"); // reccuperation de la description
        final String types = detail.getStringExtra("types"); // reccupération des types
        final int pri = Integer.parseInt(detail.getStringExtra("prix")); // reccupération du prix


        Glide.with(getApplicationContext()).load(imag).into(image); //l'affichage de l'image dans l'ImageView definit dans notre fragment
        descrip.setText(description); //Affichage du contenu de la description  reccuperé dans le TextView
        type.setText(types); //affichage du types
        prix.setText(Integer.toString(pri)+" F-CFA"); //Affichage du prix


        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


           //ici on met des setters pour pouvoir mettre le contenu de la liste
                lunette.setPhoto(imag);
                lunette.setDescription(description);
                lunette.setTypes(types);
                lunette.setPrix(pri);


                //ajout des lunettes dans la liste
                lunetteList.add(lunette);

                //appel de la methode ajouter au panier en lui passant en  paramètre la liste des lunettes
                ajoutPanier(lunetteList);





            }
        });


    }

//*****************************************************************************************
//Création de la methode de reccuperation des données et de l'affichage
//******************************************************************************************


    //le type de retour est void et ça doit nous retourné notre liste de model
    public void ajoutPanier(List<Lunette>  maliste) {


    //*************************************
    //Parcours de la liste
    //***************************************

        for (Lunette l : maliste) {

    //Ajout des élement dans la liste
            l.getTypes();
            l.getDescription();
            l.getPrix();

    //Toast de test
            Toast.makeText(getApplicationContext(), "Ajouter au panier "+l.getDescription()+""+l.getPrix(), Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
