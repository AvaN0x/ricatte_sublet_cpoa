package models;

import java.util.Objects;

public class Product {
    private int _id;
    private String _nom;
    private String _description;
    private float _tarif;
    private String _visuel;
    private Category _category;

    public Product(int _id, String _nom, String _description, float _tarif, String _visuel, Category _category) {
        this._id = _id;
        this._nom = _nom;
        this._description = _description;
        this._tarif = _tarif;
        this._visuel = _visuel;
        this._category = _category;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nom() {
        return this._nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public String get_description() {
        return this._description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public float get_tarif() {
        return this._tarif;
    }

    public void set_tarif(float _tarif) {
        this._tarif = _tarif;
    }

    public String get_visuel() {
        return this._visuel;
    }

    public void set_visuel(String _visuel) {
        this._visuel = _visuel;
    }

    public Category get_category() {
        return this._category;
    }

    public void set_category(Category _category) {
        this._category = _category;
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
                && Objects.equals(_visuel, product._visuel) && Objects.equals(_category, product._category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _nom, _description, _tarif, _visuel, _category);
    }

    @Override
    public String toString() {
        return "{" + " _id='" + get_id() + "'" + ", _nom='" + get_nom() + "'" + ", _description='" + get_description()
                + "'" + ", _tarif='" + get_tarif() + "'" + ", _visuel='" + get_visuel() + "'" + ", _category='"
                + get_category() + "'" + "}";
    }

}
