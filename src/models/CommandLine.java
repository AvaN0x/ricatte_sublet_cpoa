package models;

import java.util.Objects;

public class CommandLine {
    private Product _produit;
    private int _quantite;
    private float _tarif_unitaire;

    public CommandLine(Product produit, int quantite, float tarif_unitaire) {
        this._produit = produit;
        this._quantite = quantite;
        this._tarif_unitaire = tarif_unitaire;
    }

    public Product getProduit() {
        return this._produit;
    }

    public void setProduit(Product produit) {
        this._produit = produit;
    }

    public int getQuantite() {
        return this._quantite;
    }

    public void setQuantite(int quantite) {
        this._quantite = quantite;
    }

    public float getTarifUnitaire() {
        return this._tarif_unitaire;
    }

    public void setTarifUnitaire(float tarif_unitaire) {
        this._tarif_unitaire = tarif_unitaire;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommandLine)) {
            return false;
        }
        CommandLine commandLine = (CommandLine) o;
        return Objects.equals(_produit, commandLine._produit) && _quantite == commandLine._quantite
                && _tarif_unitaire == commandLine._tarif_unitaire;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_produit, _quantite, _tarif_unitaire);
    }

    @Override
    public String toString() {
        return "{" + " _produit='" + getProduit() + "'" + ", _quantite='" + getQuantite() + "'" + ", _tarif_unitaire='"
                + getTarifUnitaire() + "'" + "}";
    }

}
