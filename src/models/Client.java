package models;

import java.util.Objects;

public class Client {
    private int _id;
    private String _nom;
    private String _prenom;
    private String _identifiant;
    private String _motDePasse;
    private int _adrNumero;
    private String _adrVoie;
    private int _adrCodePostal;
    private String _adrVille;
    private String _adrPays;

    public Client(int id, String nom, String prenom, String identifiant, String motDePasse, int adrNumero,
            String adrVoie, int adrCodePostal, String adrVille, String adrPays) {
        this._id = id;
        this._nom = nom;
        this._prenom = prenom;
        this._identifiant = identifiant;
        this._motDePasse = motDePasse;
        this._adrNumero = adrNumero;
        this._adrVoie = adrVoie;
        this._adrCodePostal = adrCodePostal;
        this._adrVille = adrVille;
        this._adrPays = adrPays;
    }

    public Client(String nom, String prenom, String identifiant, String motDePasse, int adrNumero, String adrVoie,
            int adrCodePostal, String adrVille, String adrPays) {
        this._id = -1;
        this._nom = nom;
        this._prenom = prenom;
        this._identifiant = identifiant;
        this._motDePasse = motDePasse;
        this._adrNumero = adrNumero;
        this._adrVoie = adrVoie;
        this._adrCodePostal = adrCodePostal;
        this._adrVille = adrVille;
        this._adrPays = adrPays;
    }

    public Client(int id) {
        this._id = id;
        this._nom = "";
        this._prenom = "";
        this._identifiant = "";
        this._motDePasse = "";
        this._adrNumero = 0;
        this._adrVoie = "";
        this._adrCodePostal = 0;
        this._adrVille = "";
        this._adrPays = "";
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getNom() {
        return this._nom;
    }

    public void setNom(String nom) {
        this._nom = nom;
    }

    public String getPrenom() {
        return this._prenom;
    }

    public void setPrenom(String prenom) {
        this._prenom = prenom;
    }

    public String getIdentifiant() {
        return this._identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this._identifiant = identifiant;
    }

    public String getMotDePasse() {
        return this._motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this._motDePasse = motDePasse;
    }

    public int getAdrNumero() {
        return this._adrNumero;
    }

    public void setAdrNumero(int adrNumero) {
        this._adrNumero = adrNumero;
    }

    public String getAdrVoie() {
        return this._adrVoie;
    }

    public void setAdrVoie(String adrVoie) {
        this._adrVoie = adrVoie;
    }

    public int getAdrCodePostal() {
        return this._adrCodePostal;
    }

    public void setAdrCodePostal(int adrCodePostal) {
        this._adrCodePostal = adrCodePostal;
    }

    public String getAdrVille() {
        return this._adrVille;
    }

    public void setAdrVille(String adrVille) {
        this._adrVille = adrVille;
    }

    public String getAdrPays() {
        return this._adrPays;
    }

    public void setAdrPays(String adrPays) {
        this._adrPays = adrPays;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return _id == client._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _nom, _prenom, _identifiant, _motDePasse, _adrNumero, _adrVoie, _adrCodePostal,
                _adrVille, _adrPays);
    }

    @Override
    public String toString() {
        return "{" + " _id='" + getId() + "'" + ", _nom='" + getNom() + "'" + ", _prenom='" + getPrenom() + "'"
                + ", _identifiant='" + getIdentifiant() + "'" + ", motDePasse='" + getMotDePasse() + "'"
                + ", _adrNumero='" + getAdrNumero() + "'" + ", _adrVoie='" + getAdrVoie() + "'" + ", _adrCodePostal='"
                + getAdrCodePostal() + "'" + ", _adrVille='" + getAdrVille() + "'" + ", _adrPays='" + getAdrPays() + "'"
                + "}";
    }

}
