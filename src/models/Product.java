package models;

import java.util.Objects;

public class Product implements IBaseModel {
    private int id;
    private String nom;
    private String description;
    private float tarif;
    private String visuel;
    private Category categorie;

    public Product(int id, String nom, String description, float tarif, String visuel, Category categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.tarif = tarif;
        this.visuel = visuel;
        this.categorie = categorie;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTarif() {
        return this.tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public String getVisuel() {
        return this.visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public Category getCategorie() {
        return this.categorie;
    }

    public void setCategorie(Category categorie) {
        this.categorie = categorie;
    }

    public Product id(int id) {
        this.id = id;
        return this;
    }

    public Product nom(String nom) {
        this.nom = nom;
        return this;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public Product tarif(float tarif) {
        this.tarif = tarif;
        return this;
    }

    public Product visuel(String visuel) {
        this.visuel = visuel;
        return this;
    }

    public Product categorie(Category categorie) {
        this.categorie = categorie;
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
        return id == product.id && Objects.equals(nom, product.nom) && Objects.equals(description, product.description)
                && tarif == product.tarif && Objects.equals(visuel, product.visuel)
                && Objects.equals(categorie, product.categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, description, tarif, visuel, categorie);
    }

    @Override
    public String toString() {
        return getNom();
    }

}
