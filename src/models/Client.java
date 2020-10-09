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
        if (id < 0)
            throw new IllegalArgumentException("id can't be a negative");
        this._id = id;

        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("\"Nom\" can't be empty or null");
        this._nom = nom;

        if (prenom == null || prenom.trim().isEmpty())
            throw new IllegalArgumentException("\"Prenom\" can't be empty or null");
        this._prenom = prenom;

        if (identifiant == null || identifiant.trim().isEmpty())
            throw new IllegalArgumentException("\"Identifiant\" can't be empty or null");
        this._identifiant = identifiant;

        if (motDePasse == null || motDePasse.trim().isEmpty())
            throw new IllegalArgumentException("\"MotDePasse\" can't be empty or null");
        this._motDePasse = motDePasse;

        if (adrNumero < 0)
            throw new IllegalArgumentException("\"Numero\" can't be a negative");
        this._adrNumero = adrNumero;

        if (adrVoie == null || adrVoie.trim().isEmpty())
            throw new IllegalArgumentException("\"Voie\" can't be empty or null");
        this._adrVoie = adrVoie;

        if (adrCodePostal < 1000)
            throw new IllegalArgumentException("\"CodePostal\" must be valid");
        this._adrCodePostal = adrCodePostal;

        if (adrVille == null || adrVille.trim().isEmpty())
            throw new IllegalArgumentException("\"Ville\" can't be empty or null");
        this._adrVille = adrVille;

        if (adrPays == null || adrPays.trim().isEmpty())
            throw new IllegalArgumentException("\"Pays\" can't be empty or null");
        this._adrPays = adrPays;
    }

    public Client(String nom, String prenom, String identifiant, String motDePasse, int adrNumero, String adrVoie,
            int adrCodePostal, String adrVille, String adrPays) {
        this(0, nom, prenom, identifiant, motDePasse, adrNumero, adrVoie, adrCodePostal, adrVille, adrPays);
    }

    public Client(int id) {
        this(id, "null", "null", "null", "null", 0, "null", 0, "null", "null");
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
            throw new IllegalArgumentException("\"Nom\" can't be empty or null");
        this._nom = nom;
    }

    public String getPrenom() {
        return this._prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.trim().isEmpty())
            throw new IllegalArgumentException("\"Prenom\" can't be empty or null");
        this._prenom = prenom;
    }

    public String getIdentifiant() {
        return this._identifiant;
    }

    public void setIdentifiant(String identifiant) {
        if (identifiant == null || identifiant.trim().isEmpty())
            throw new IllegalArgumentException("\"Identifiant\" can't be empty or null");
        this._identifiant = identifiant;
    }

    public String getMotDePasse() {
        return this._motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        if (motDePasse == null || motDePasse.trim().isEmpty())
            throw new IllegalArgumentException("\"MotDePasse\" can't be empty or null");
        this._motDePasse = motDePasse;
    }

    public int getAdrNumero() {
        return this._adrNumero;
    }

    public void setAdrNumero(int adrNumero) {
        if (adrNumero < 0)
            throw new IllegalArgumentException("\"Numero\" can't be a negative");
        this._adrNumero = adrNumero;
    }

    public String getAdrVoie() {
        return this._adrVoie;
    }

    public void setAdrVoie(String adrVoie) {
        if (adrVoie == null || adrVoie.trim().isEmpty())
            throw new IllegalArgumentException("\"Voie\" can't be empty or null");
        this._adrVoie = adrVoie;
    }

    public int getAdrCodePostal() {
        return this._adrCodePostal;
    }

    public void setAdrCodePostal(int adrCodePostal) {
        if (adrCodePostal < 1000)
            throw new IllegalArgumentException("\"CodePostal\" must be valid");
        this._adrCodePostal = adrCodePostal;
    }

    public String getAdrVille() {
        return this._adrVille;
    }

    public void setAdrVille(String adrVille) {
        if (adrVille == null || adrVille.trim().isEmpty())
            throw new IllegalArgumentException("\"Ville\" can't be empty or null");
        this._adrVille = adrVille;
    }

    public String getAdrPays() {
        return this._adrPays;
    }

    public void setAdrPays(String adrPays) {
        if (adrPays == null || adrPays.trim().isEmpty())
            throw new IllegalArgumentException("\"Pays\" can't be empty or null");
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
        return getNom() + " " + getPrenom() + " (" + getIdentifiant() + "), " + getAdrPays();
    }

}
