package models;

import java.util.Objects;

public class Product {
    private int _id;
    private String _nom;
    private String _description;
    private float _tarif;
    private String _visuel;
    private Category _category;

    public Product(int id, String nom, String description, float tarif, String visuel, Category category) {
        this._id = id;
        this._nom = nom;
        this._description = description;
        this._tarif = tarif;
        this._visuel = visuel;
        this._category = category;
    }

    public Product(String nom, String description, float tarif, String visuel, Category category) {
        this._id = 0;
        this._nom = nom;
        this._description = description;
        this._tarif = tarif;
        this._visuel = visuel;
        this._category = category;
    }

    public Product(int id) {
        this._id = id;
        this._nom = "";
        this._description = "";
        this._tarif = 0;
        this._visuel = "";
        this._category = null;
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

    public Category getCategory() {
        return this._category;
    }

    public void setCategory(Category category) {
        this._category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return _id == product._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _nom, _description, _tarif, _visuel, _category);
    }

    @Override
    public String toString() {
        return getNom();
    }

}
