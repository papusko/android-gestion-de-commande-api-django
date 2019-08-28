package com.africa.cloud.commandes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clients {

  @SerializedName("nom")
  @Expose
  private
  String nom;

  @SerializedName("prenom")
  @Expose
  private
  String prenom;

  @SerializedName("adresse")
  @Expose
  private
  String adresse;

  @SerializedName("telephone")
  @Expose
  private
  String telephone;

  @SerializedName("photo")
  @Expose
  private
  String photo;


  public Clients(String nom, String prenom, String adresse, String telephone, String photo) {
    this.nom = nom;
    this.prenom = prenom;
    this.adresse = adresse;
    this.telephone = telephone;
    this.photo = photo;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}