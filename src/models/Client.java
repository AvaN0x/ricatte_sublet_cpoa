package models;

import java.util.Objects;

public class Client implements IBaseModel {
    private int _id;
    private String _nom;
    private String _prenom;

    public Client(int id, String nom, String prenom) {
        this._id = id;
        this._nom = nom;
        this._prenom = prenom;
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

    public Client id(int id) {
        this._id = id;
        return this;
    }

    public Client nom(String nom) {
        this._nom = nom;
        return this;
    }

    public Client prenom(String prenom) {
        this._prenom = prenom;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return _id == client._id && Objects.equals(_nom, client._nom) && Objects.equals(_prenom, client._prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _nom, _prenom);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nom='" + getNom() + "'" + ", prenom='" + getPrenom() + "'" + "}";
    }

}
