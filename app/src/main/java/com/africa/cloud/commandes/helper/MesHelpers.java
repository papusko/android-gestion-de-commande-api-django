package com.africa.cloud.commandes.helper;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.africa.cloud.commandes.model.Carte;
import com.africa.cloud.commandes.model.Lunette;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MesHelpers {

   Realm realm;

   Carte maCarte = new Carte();

    public MesHelpers(Realm realm) {
        this.realm = realm;
    }





            public  void ajouterCarte()
            {

                Realm realm;
                //configuration du realm dans ma nouvelle activity
                RealmConfiguration config = new RealmConfiguration.Builder().build();
                realm = Realm.getInstance(config);




            }





    //reccperation de la liste
    public List<Carte> reccuperation() {
        Realm realm;
        //configuration du realm dans ma nouvelle activity
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(config);
        List<Carte> carteList = new ArrayList<>();

        RealmResults<Carte> mCartes = realm.where(Carte.class).findAll();
        for (Carte c : carteList) {
            carteList.add(c);

            System.out.println(carteList);
        }

        return  mCartes;
    }

}
