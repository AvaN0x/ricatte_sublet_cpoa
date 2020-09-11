package controllers;

import java.util.ArrayList;

import models.Client;

public class ClientController extends BaseController<Client> {

    public ClientController() {
        // getDistantClients();
    }

    /**
     * Add a new client to the list. The id is automaticly generated.
     * 
     * @param nom    The name of the client.
     * @param prenom The first name of the client.
     */
    public void addObject(String nom, String prenom) {
        int id;
        if (objects.size() == 0)
            id = 1;
        else
            id = objects.get(objects.size() - 1).getId() + 1;
        addObject(new Client(id, nom, prenom));
    }

    public void addObject(Client cli) {
        int lastId = objects.get(objects.size() - 1).getId();
        if (cli.getId() < lastId)
            cli.setId(lastId + 1);
        objects.add(cli);
        // TODO: Sql.addClient(cli);
    }

    /**
     * Edit a client in the list. The new name and visual can be null but not at the
     * same time.
     * 
     * @param cli       The client to edit.
     * @param newNom    The new name of the client. Can be null.
     * @param newPrenom The new first name of the Client. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new name and first name are null.
     */
    public boolean editObject(Client cli, String newNom, String newPrenom) throws IllegalArgumentException {
        return editObject(cli.getId(), newNom, newPrenom);
    }

    /**
     * Edit a client in the list. The new name and first name can be null but not at
     * the same time.
     * 
     * @param id        The id of the client.
     * @param newNom    The new name of the client.
     * @param newPrenom The new first name of the Client.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new name and first name are null.
     */
    public boolean editObject(int id, String newNom, String newPrenom) throws IllegalArgumentException {
        if (newNom == null && newPrenom == null)
            throw new IllegalArgumentException();

        for (Client client : objects)
            if (client.getId() == id) {
                if (newNom == null)
                    newNom = client.getNom();
                if (newPrenom == null)
                    newPrenom = client.getPrenom();

                client.setNom(newNom);
                client.setPrenom(newPrenom);
                // TODO: Sql.updateClient(id, newNom, newPrenom);
                return true;
            }
        return false;
    }

    public boolean removeObject(int id) {
        var res = objects.removeIf(cli -> cli.getId() == id);
        // TODO: Sql.remClient(id);
        return res;
    }

    public ArrayList<Client> getDistantObjects() {
        // TODO: clients = Sql.getCategories();
        return getObjects();
    }
}
