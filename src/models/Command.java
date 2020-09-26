package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Command {
    private int _id;
    private LocalDate _date_command;
    private Client _client;
    private HashMap<Product, CommandLine> _commandLines; // HashMap<id_produit, commandLine>

    public Command(int id, LocalDate date_command, Client client) {
        this._id = id;
        this._date_command = date_command;
        this._client = client;
        this._commandLines = new HashMap<Product, CommandLine>();
    }

    public Command(int id, LocalDate date_command, Client client, HashMap<Product, CommandLine> commandLines) {
        this._id = id;
        this._date_command = date_command;
        this._client = client;
        this._commandLines = commandLines;
    }

    public Command(LocalDate date_command, Client client, HashMap<Product, CommandLine> commandLines) {
        this._id = 0;
        this._date_command = date_command;
        this._client = client;
        this._commandLines = commandLines;
    }

    public Command(int id) {
        this._id = id;
        this._date_command = null;
        this._client = null;
        this._commandLines = null;
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public LocalDate getDateCommand() {
        return this._date_command;
    }

    public void setDateCommand(LocalDate date_command) {
        this._date_command = date_command;
    }

    public Client getClient() {
        return this._client;
    }

    public void setClient(Client client) {
        this._client = client;
    }

    public HashMap<Product, CommandLine> getCommandLines() {
        return this._commandLines;
    }

    public void setCommandLines(HashMap<Product, CommandLine> commandLines) {
        this._commandLines = commandLines;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Command)) {
            return false;
        }
        Command command = (Command) o;
        return _id == command._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _date_command, _client, _commandLines);
    }

    @Override
    public String toString() {
        return getId() + " " + getDateCommand();
    }

}
