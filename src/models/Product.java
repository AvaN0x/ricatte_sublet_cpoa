package models;

import java.util.Objects;

public class Product implements IBaseModel {
    private int _id;
    private String _nom;
    private String _description;
    private float _tarif;
    private String _visuel;
    private Category _categorie;

    public Product(int id, String nom, String description, float tarif, String visuel, Category categorie) {
        this._id = id;
        this._nom = nom;
        this._description = description;
        this._tarif = tarif;
        this._visuel = visuel;
        this._categorie = categorie;
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

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public float getTarif() {
        return this._tarif;
    }

    public void setTarif(float tarif) {
        this._tarif = tarif;
    }

    public String getVisuel() {
        return this._visuel;
    }

    public void setVisuel(String visuel) {
        this._visuel = visuel;
    }

    public Category getCategorie() {
        return this._categorie;
    }

    public void setCategorie(Category categorie) {
        this._categorie = categorie;
    }

    public Product id(int id) {
        this._id = id;
        return this;
    }

    public Product nom(String nom) {
        this._nom = nom;
        return this;
    }

    public Product description(String description) {
        this._description = description;
        return this;
    }

    public Product tarif(float tarif) {
        this._tarif = tarif;
        return this;
    }

    public Product visuel(String visuel) {
        this._visuel = visuel;
        return this;
    }

    public Product categorie(Category categorie) {
        this._categorie = categorie;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return _id == product._id && Objects.equals(_nom, product._nom)
                && Objects.equals(_description, product._description) && _tarif == product._tarif
                && Objects.equals(_visuel, product._visuel) && Objects.equals(_categorie, product._categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _nom, _description, _tarif, _visuel, _categorie);
    }

    @Override
    public String toString() {
        return getNom();
    }

}
