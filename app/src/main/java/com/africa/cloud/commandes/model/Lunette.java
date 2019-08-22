package com.africa.cloud.commandes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lunette {

    @SerializedName("types")
    @Expose
    private
    String types;

    @SerializedName("photo")
    @Expose
    private
    String photo;

    public Lunette(String types, String photo) {
        this.types = this.types;
        this.photo = this.photo;
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
}
