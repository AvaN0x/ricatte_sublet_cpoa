package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

public class Command {
    private int _id;
    private LocalDate _date_command;
    private Client _client;
    private HashMap<Product, CommandLine> _commandLines; // HashMap<id_produit, commandLine>

    public Command(int id, LocalDate date_command, Client client, HashMap<Product, CommandLine> commandLines) {
        if (id < 0)
            throw new IllegalArgumentException("id can't be a negative");
        this._id = id;

        if (date_command == null)
            throw new IllegalArgumentException("\"Date Command\" can't be null");
        this._date_command = date_command;

        if (client == null)
            throw new IllegalArgumentException("\"Client\" can't be null");
        this._client = client;

        if (commandLines == null)
            throw new IllegalArgumentException("\"CommandLines\" can't be null");
        this._commandLines = commandLines;
    }

    public Command(int id, LocalDate date_command, Client client) {
        this(id, date_command, client, new HashMap<Product, CommandLine>());
    }

    public Command(LocalDate date_command, Client client, HashMap<Product, CommandLine> commandLines) {
        this(0, date_command, client, commandLines);
    }

    public Command(LocalDate date_command, Client client) {
        this(0, date_command, client);
    }

    public Command(int id) {
        this(id, LocalDate.now(), new Client(0));
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id can't be a negative");
        this._id = id;
    }

    public LocalDate getDateCommand() {
        return this._date_command;
    }

    public void setDateCommand(LocalDate date_command) {
        if (date_command == null)
            throw new IllegalArgumentException("\"Date Command\" can't be null");
        this._date_command = date_command;
    }

    public Client getClient() {
        return this._client;
    }

    public void setClient(Client client) {
        if (client == null)
            throw new IllegalArgumentException("\"Client\" can't be null");
        this._client = client;
    }

    public HashMap<Product, CommandLine> getCommandLines() {
        return this._commandLines;
    }

    public void setCommandLines(HashMap<Product, CommandLine> commandLines) {
        if (commandLines == null)
            throw new IllegalArgumentException("\"CommandLines\" can't be null");
        this._commandLines = commandLines;
    }

    public void addCommandLine(Product prod, CommandLine line) {
        this._commandLines.put(prod, line);
    }

    public void updateCommandLine(Product prod, CommandLine line) {
        if (this._commandLines.containsKey(prod))
            this._commandLines.put(prod, line);
        else
            throw new IllegalArgumentException("This product is not in the command");
    }

    public void remCommandLine(Product prod) {
        if (this._commandLines.containsKey(prod))
            this._commandLines.remove(prod);
        else
            throw new IllegalArgumentException("This product is not in the command");
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
        return getId() + " " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(getDateCommand());
    }

}
