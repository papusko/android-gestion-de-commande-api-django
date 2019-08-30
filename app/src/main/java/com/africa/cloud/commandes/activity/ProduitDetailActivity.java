package com.africa.cloud.commandes.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.commandes.LunetteFragment;
import com.africa.cloud.commandes.R;
import com.africa.cloud.commandes.model.Carte;
import com.africa.cloud.commandes.model.Lunette;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ProduitDetailActivity extends AppCompatActivity implements LunetteFragment.OnFragmentInteractionListener {
    private Lunette lunette;
    private Carte carte;
    private List<Carte> carteList;
    private List<Lunette> lunetteList;
    int pri;
    String imag, description, types;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_detail);


        realm.init(this); //initialisation du realm



        //configuration du realm qui donne comme nom  de fichier par defaut ("default.realm") nous allons donner un nom et une version a notre realm
        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .name("panier.db") //affectation de nom creer par Realm pour stocké les données
                        .schemaVersion(1) //creer une version
                        .deleteRealmIfMigrationNeeded()
                        .build();
        realm = Realm.getInstance(config);





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
         imag = detail.getStringExtra("image"); //reccuperation de l'image
         description = detail.getStringExtra("description"); // reccuperation de la description
         types = detail.getStringExtra("types"); // reccupération des types
         pri = Integer.parseInt(detail.getStringExtra("prix")); // reccupération du prix


        Glide.with(getApplicationContext()).load(imag).into(image); //l'affichage de l'image dans l'ImageView definit dans notre fragment
        descrip.setText(description); //Affichage du contenu de la description  reccuperé dans le TextView
        type.setText(types); //affichage du types
        prix.setText(Integer.toString(pri)+" F-CFA"); //Affichage du prix


        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


           //ici on met des setters pour pouvoir mettre le contenu de la liste
                lunette.setPhoto (imag);
                lunette.setDescription (description);
                lunette.setTypes (types);
                lunette.setPrix (pri);


                //ajout des lunettes dans la liste
                lunetteList.add(lunette);

                //appel de la methode ajouter au panier en lui passant en  paramètre la liste des lunettes
                ajoutPanier(carteList);


                Intent panier = new Intent(ProduitDetailActivity.this, PanierActivity.class);
                startActivity(panier);



            }
        });


    }

//*****************************************************************************************
//Création de la methode de reccuperation des données et de l'affichage
//******************************************************************************************


    //le type de retour est void et ça doit nous retourné notre liste de model
    public void ajoutPanier(List<Carte>  maliste) {


    //*************************************
    //Parcours de la liste
    //***************************************

/*

        for (Lunette l : maliste) {

    //Ajout des élement dans la liste
            l.getTypes();
            l.getDescription();
            l.getPrix();



    //Toast de test
            Toast.makeText(getApplicationContext(), "Ajouter au panier "+l.getDescription()+" "+l.getPrix()+" "+l.getTypes(), Toast.LENGTH_SHORT).show();
        }


*/

        realm = Realm.getDefaultInstance();







  /*      String types = ;
        mNumeroTelephone = edit_numero_telephone.getText().toString();
        mDateNaissance = edit_date_naissance.getText().toString();
        mEmail = edit_mail.getText().toString();
*/




        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                Carte c = new Carte();

                if (realm!=null)
                {
                    Log.e("ppppppp", "Base de données créer avec succes");
                  //  c.setPhoto(carte.getPhoto());
                    c.setPhoto(imag);
                    c.setTypes(types);
                    c.setDescription(description);
                    c.setPrix(pri);
                    realm.copyToRealm(c);
                    Toast.makeText(getApplicationContext(),"enregistrement reussi",Toast.LENGTH_LONG).show();

                }

            }
        });



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
