package models;

import java.util.Objects;

public class Client {
    private int _id;
    private String _nom;
    private String _prenom;
    private String _identifiant;
    private String mot_de_passe;
    private int _adr_numero;
    private String _adr_voie;
    private int _adr_code_postal;
    private String _adr_ville;
    private String _adr_pays;

    public Client(int _id, String _nom, String _prenom, String _identifiant, String mot_de_passe, int _adr_numero,
            String _adr_voie, int _adr_code_postal, String _adr_ville, String _adr_pays) {
        this._id = _id;
        this._nom = _nom;
        this._prenom = _prenom;
        this._identifiant = _identifiant;
        this.mot_de_passe = mot_de_passe;
        this._adr_numero = _adr_numero;
        this._adr_voie = _adr_voie;
        this._adr_code_postal = _adr_code_postal;
        this._adr_ville = _adr_ville;
        this._adr_pays = _adr_pays;
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

    public String get_identifiant() {
        return this._identifiant;
    }

    public void set_identifiant(String _identifiant) {
        this._identifiant = _identifiant;
    }

    public String getMot_de_passe() {
        return this.mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public int get_adr_numero() {
        return this._adr_numero;
    }

    public void set_adr_numero(int _adr_numero) {
        this._adr_numero = _adr_numero;
    }

    public String get_adr_voie() {
        return this._adr_voie;
    }

    public void set_adr_voie(String _adr_voie) {
        this._adr_voie = _adr_voie;
    }

    public int get_adr_code_postal() {
        return this._adr_code_postal;
    }

    public void set_adr_code_postal(int _adr_code_postal) {
        this._adr_code_postal = _adr_code_postal;
    }

    public String get_adr_ville() {
        return this._adr_ville;
    }

    public void set_adr_ville(String _adr_ville) {
        this._adr_ville = _adr_ville;
    }

    public String get_adr_pays() {
        return this._adr_pays;
    }

    public void set_adr_pays(String _adr_pays) {
        this._adr_pays = _adr_pays;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return _id == client._id && Objects.equals(_nom, client._nom) && Objects.equals(_prenom, client._prenom)
                && Objects.equals(_identifiant, client._identifiant)
                && Objects.equals(mot_de_passe, client.mot_de_passe) && _adr_numero == client._adr_numero
                && Objects.equals(_adr_voie, client._adr_voie) && _adr_code_postal == client._adr_code_postal
                && Objects.equals(_adr_ville, client._adr_ville) && Objects.equals(_adr_pays, client._adr_pays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _nom, _prenom, _identifiant, mot_de_passe, _adr_numero, _adr_voie, _adr_code_postal,
                _adr_ville, _adr_pays);
    }

    @Override
    public String toString() {
        return "{" + " _id='" + get_id() + "'" + ", _nom='" + get_nom() + "'" + ", _prenom='" + get_prenom() + "'"
                + ", _identifiant='" + get_identifiant() + "'" + ", mot_de_passe='" + getMot_de_passe() + "'"
                + ", _adr_numero='" + get_adr_numero() + "'" + ", _adr_voie='" + get_adr_voie() + "'"
                + ", _adr_code_postal='" + get_adr_code_postal() + "'" + ", _adr_ville='" + get_adr_ville() + "'"
                + ", _adr_pays='" + get_adr_pays() + "'" + "}";
    }

}
