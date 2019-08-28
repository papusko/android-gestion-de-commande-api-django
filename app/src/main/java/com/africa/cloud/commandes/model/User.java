package com.africa.cloud.commandes.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

  @SerializedName("prenom")
  @Expose
  private
  String prenom;

  @SerializedName("nom")
  @Expose
  private
  String nom;

  @SerializedName("username")
  @Expose
  private
  String username;

  @SerializedName("email")
  @Expose
  private
  String email;

  @SerializedName("password")
  @Expose
  private
  String password;

  @SerializedName("Confirmpassword")
  @Expose
  private
  String confirmPassword;

  String token;

  public User(String prenom, String nom, String username, String email, String password, String confirmPassword) {
    this.prenom = prenom;
    this.nom = nom;
    this.username = username;
    this.email = email;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.confirmPassword = token;
  }
}