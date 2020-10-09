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
        if (id < 0)
            throw new IllegalArgumentException("id can't be a negative");
        this._id = id;

        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("nom can't be empty or null");
        this._nom = nom;

        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("description can't be empty or null");
        this._description = description;

        if (tarif <= 0)
            throw new IllegalArgumentException("tarif can't be a negative or null");
        this._tarif = tarif;

        if (visuel == null || visuel.trim().isEmpty())
            throw new IllegalArgumentException("description can't be empty or null");
        this._visuel = visuel;

        if (category == null)
            throw new IllegalArgumentException("category can't be null");
        this._category = category;
    }

    public Product(String nom, String description, float tarif, String visuel, Category category) {
        this(0, nom, description, tarif, visuel, category);
    }

    public Product(int id) {
        this(id, "", "", 0, "", null);
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id can't be a negative");
        this._id = id;
    }

    public String getNom() {
        return this._nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("nom can't be empty or null");
        this._nom = nom;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("description can't be empty or null");
        this._description = description;
    }

    public float getTarif() {
        return this._tarif;
    }

    public void setTarif(float tarif) {
        if (tarif <= 0)
            throw new IllegalArgumentException("tarif can't be a negative or null");
        this._tarif = tarif;
    }

    public String getVisuel() {
        return this._visuel;
    }

    public void setVisuel(String visuel) {
        if (visuel == null || visuel.trim().isEmpty())
            throw new IllegalArgumentException("description can't be empty or null");
        this._visuel = visuel;
    }

    public Category getCategory() {
        return this._category;
    }

    public void setCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("category can't be null");
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
