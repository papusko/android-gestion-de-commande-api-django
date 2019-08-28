package com.africa.cloud.commandes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lunette {

    @SerializedName("types")
    @Expose
    private
    String types;

    @SerializedName("photo")
    @Expose
    private
    String photo;

    @SerializedName("description")
    @Expose
    private
    String description;

    @SerializedName("prix")
    @Expose
    private
    int prix;

    public Lunette() {
        this.description = description;
        this.prix = prix;
        this.types = types;
        this.photo = photo;
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
