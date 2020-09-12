package controllers;

import java.util.ArrayList;

import models.Client;

public class ClientController extends BaseController<Client> {

    public void addObject(Client cli) {
        super.addObject(cli);
        ClientSql.addObject(cli);
    }

    /**
     * Edit a client in the list.
     * 
     * @param cli       The client to edit.
     * @param newNom    The new name of the client
     * @param newPrenom The new first name of the Client
     * @return If the editing is successfull or not.
     */
    public boolean editObject(Client cli, String newNom, String newPrenom) throws InstantiationException,
            IllegalAccessException, java.lang.reflect.InvocationTargetException, SecurityException {
        return editObject(cli.getId(), newNom, newPrenom);
    }

    /**
     * Edit a client in the list.
     * 
     * @param id        The id of the client.
     * @param newNom    The new name of the client.
     * @param newPrenom The new first name of the Client.
     * @return If the editing is successfull or not.
     */
    public boolean editObject(int id, String newNom, String newPrenom) throws InstantiationException,
            IllegalAccessException, java.lang.reflect.InvocationTargetException, SecurityException {
        boolean isSucess = super.editObject(id, newNom, newPrenom);
        if (isSucess) {
            ClientSql.updateObject(id, newNom, newPrenom);
            return true;
        }
        return false;
    }

    /**
     * Remove a product in the list.
     * 
     * @param id The id of the product
     * @return If the removing is sucessfull or not.
     */
    public boolean removeObject(int id) {
        ClientSql.remObject(id);
        return super.removeObject(id);
    }

    /**
     * Get all the distant cliories.
     * 
     * @return The ArrayList containing the cliories.
     */
    public ArrayList<Client> getDistantObjects() {
        _objects = ClientSql.getObjects();
        return getObjects();
    }
}
