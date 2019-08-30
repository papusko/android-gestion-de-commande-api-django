package com.africa.cloud.commandes.model;

import io.realm.Realm;
import io.realm.RealmObject;

public class Carte extends RealmObject {


    private
    String types;

    private
    String photo;

    private
    String description;


    private
    int prix;


    public Carte() {
        this.types = types;
        this.photo = photo;
        this.description = description;
        this.prix = prix;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
