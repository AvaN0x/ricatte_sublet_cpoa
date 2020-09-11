package controllers;

import java.util.ArrayList;

import models.Client;

public class ClientController {
    private ArrayList<Client> clients = new ArrayList<Client>();

    public ClientController() {
        getDistantClients();
    }

    /**
     * Add a new client to the list. The id is automaticly generated.
     * @param nom The name of the client.
     * @param prenom The first name of the client.
     */
    public void addClient(String nom, String prenom) {
        int id;
        if (clients.size() == 0)
            id = 1;
        else
            id = clients.get(clients.size() - 1).getId() + 1;
        addClient(new Client(id, nom, prenom));
    }

    /**
     *  Add a new client to the list. The id is automaticly generated.
     * @param cli The client to add.
     */
    public void addClient(Client cli) {
        int lastId = clients.get(clients.size() - 1).getId();
        if (cli.getId() < lastId)
            cli.setId(lastId + 1);
        clients.add(cli);
        //TODO: Sql.addClient(cli);
    }

    /**
     * Edit a client in the list. The new name and visual can be null but not at the same time.
     * @param cli The client to edit. 
     * @param newNom The new name of the client. Can be null.
     * @param newPrenom The new first name of the Client. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new name and first name are null.
     */
    public boolean editClient(Client cli, String newNom, String newPrenom) throws IllegalArgumentException {
        return editClient(cli.getId(), newNom, newPrenom);
    }

    /**
     * Edit a client in the list. The new name and first name can be null but not at the same time.
     * @param id The id of the client.
     * @param newNom The new name of the client.
     * @param newPrenom The new first name of the Client.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new name and first name are null.
     */
    public boolean editClient(int id, String newNom, String newPrenom) throws IllegalArgumentException {
        if (newNom == null && newPrenom == null)
            throw new IllegalArgumentException();

        for (Client client : clients)
            if (client.getId() == id) {
                if (newNom == null)
                    newNom = client.getNom();
                if (newPrenom == null)
                    newPrenom = client.getPrenom();

                client.setNom(newNom);
                client.setPrenom(newPrenom);
                //TODO: Sql.updateClient(id, newNom, newPrenom);
                return true;
            }
        return false;
    }

    /**
     * Remove a client in the list.
     * @param cli The client to remove.
     * @return If the removing is sucessfull or not.
     */
    public boolean removeClient(Client cli) {
        return removeClient(cli.getId());
    }

    /**
     * Remove a client in the list.
     * @param id The id of the client
     * @return If the removing is sucessfull or not.
     */
    public boolean removeClient(int id) {
        var res = clients.removeIf(cli -> cli.getId() == id);
        //TODO: Sql.remClient(id);
        return res;
    }

    /**
     * Get all the distant cliories.
     * @return The ArrayList containing the cliories.
     */
    public ArrayList<Client> getDistantClients() {
        //TODO: clients = Sql.getCategories();
        return getClients();
    }

    /**
     * Get all the cliories.
     * @return The ArrayList containing the cliories.
     */
    public ArrayList<Client> getClients(){
        if (clients == null)
            clients = new ArrayList<Client>();
        return clients;
    }
}
