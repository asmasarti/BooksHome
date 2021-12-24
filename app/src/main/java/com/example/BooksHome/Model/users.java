package com.example.BooksHome.Model;

import android.widget.EditText;

public class users
{
    private String prenom,nom,numerotel,adresse,motdepasse,confirmerpass;
    public users(){

    }

    public users(String prenom, String nom, String numerotel, String adresse, String motdepasse, String confirmerpass) {
        this.prenom = prenom;
        this.nom = nom;
        this.numerotel = numerotel;
        this.adresse = adresse;
        this.motdepasse = motdepasse;
        this.confirmerpass = confirmerpass;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getNumerotel() {
        return numerotel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public String getConfirmerpass() {
        return confirmerpass;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumerotel(String numerotel) {
        this.numerotel = numerotel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public void setConfirerpass(String confirmerpass) {
        this.confirmerpass = confirmerpass;
    }

}
