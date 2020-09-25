package models;

import java.util.Objects;

public class CommandLine {
    // HashMap<id_produit, commandLine>
    private Command _command;
    private int _quantite;
    private float _tarif_unitaire;

    public CommandLine(Command command, int quantite, float tarif_unitaire) {
        this._command = command;
        this._quantite = quantite;
        this._tarif_unitaire = tarif_unitaire;
    }

    public Command getCommand() {
        return this._command;
    }

    public void setCommand(Command command) {
        this._command = command;
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
        return Objects.equals(_command, commandLine._command) && _quantite == commandLine._quantite
                && _tarif_unitaire == commandLine._tarif_unitaire;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_command, _quantite, _tarif_unitaire);
    }

    @Override
    public String toString() {
        return "{" + " _produit='" + getCommand() + "'" + ", _quantite='" + getQuantite() + "'" + ", _tarif_unitaire='"
                + getTarifUnitaire() + "'" + "}";
    }

}
