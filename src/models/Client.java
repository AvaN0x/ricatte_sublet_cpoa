package models;

import java.util.Objects;

public class Client {
    private int _id;
    private String _nom;
    private String _prenom;

    public Client(int _id, String _nom, String _prenom) {
        this._id = _id;
        this._nom = _nom;
        this._prenom = _prenom;
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

    public String get_prenom() {
        return this._prenom;
    }

    public void set_prenom(String _prenom) {
        this._prenom = _prenom;
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
        return "{" + " _id='" + get_id() + "'" + ", _nom='" + get_nom() + "'" + ", _prenom='" + get_prenom() + "'"
                + "}";
    }

}
