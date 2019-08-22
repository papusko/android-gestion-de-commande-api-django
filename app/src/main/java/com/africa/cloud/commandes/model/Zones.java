package com.africa.cloud.commandes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zones {


    @SerializedName("nom")
    @Expose
    private
    String nom;

    public Zones(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
