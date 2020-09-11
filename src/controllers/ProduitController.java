package controllers;

import java.util.ArrayList;

import models.Produit;
import models.Category;

public class ProduitController /*extends Controller*/ {
    private ArrayList<Produit> products = new ArrayList<Produit>();

    public ProduitController() {
        getDistantProducts();
    }

    /**
     * Add a new product to the list. The id is automaticly generated.
     * @param nom The name of the product.
     * @param description The description of the product.
     * @param tarif The price of the product.
     * @param visuel The path to the image of the product.
     * @param categorie The category of the product.
     */
    public void addProduit(String nom, String description, float tarif, String visuel, Category categorie) {
        int id;
        if (products.size() == 0)
            id = 1;
        else
            id = products.get(products.size() - 1).getId() + 1;
        addProduit(new Produit(id, nom, description, tarif, visuel, categorie));
    }

    //TODO: Abstract
    /**
     *  Add a new product to the list. The id is automaticly generated.
     * @param prod The product to add.
     */
    public void addProduit(Produit prod) {
        int lastId = products.get(products.size() - 1).getId();
        if (prod.getId() < lastId)
            prod.setId(lastId + 1);
        products.add(prod);
        //TODO: Sql.addProduit(prod);
    }

    /**
     * Edit a product in the list. Except `prod`, all the parameters can be null but not at the same time.
     * @param prod The product to edit. 
     * @param newNom The name of the product. Can be null.
     * @param newDescription The description of the product. Can be null.
     * @param newTarif The price of the product. Can be null.
     * @param newVisuel The path to the image of the product. Can be null.
     * @param newCategorie The category of the product. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If all of the optional parameters are null.
     */
    public boolean editProduit(Produit prod, String newNom, String newDescription, float newTarif, String newVisuel, Category newCategorie) throws IllegalArgumentException {
        return editProduit(prod.getId(), newNom, newDescription, newTarif, newVisuel, newCategorie);
    }

    /**
     * Edit a product in the list. Except `id`, all the parameters can be null but not at the same time.
     * @param id The id of the product.
     * @param newNom The name of the product. Can be null.
     * @param newDescription The description of the product. Can be null.
     * @param newTarif The price of the product.
     * @param newVisuel The path to the image of the product. Can be null.
     * @param newCategorie The category of the product. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new name and first name are null.
     */
    public boolean editProduit(int id, String newNom, String newDescription, float newTarif, String newVisuel, Category newCategorie) throws IllegalArgumentException {
        if (newNom == null && newDescription == null && newVisuel == null && newCategorie == null)
            throw new IllegalArgumentException();

        for (Produit product : products)
            if (product.getId() == id) {
                if (newNom == null)
                    newNom = product.getNom();
                if (newDescription == null)
                    newDescription = product.getDescription();
                if (newVisuel == null)
                    newVisuel = product.getVisuel();
                if (newCategorie == null)
                    newCategorie = product.getCategorie();

                product.setNom(newNom);
                product.setDescription(newDescription);
                product.setTarif(newTarif);
                product.setVisuel(newVisuel);
                product.setCategorie(newCategorie);
                //TODO: Sql.updateProduit(id, newNom, newPrenom);
                return true;
            }
        return false;
    }

    //TODO: Factorize
    /**
     * Remove a product in the list.
     * @param prod The product to remove.
     * @return If the removing is sucessfull or not.
     */
    public boolean removeProduit(Produit prod) {
        return removeProduit(prod.getId());
    }

    //TODO: Abstract
    /**
     * Remove a product in the list.
     * @param id The id of the product
     * @return If the removing is sucessfull or not.
     */
    public boolean removeProduit(int id) {
        var res = products.removeIf(prod -> prod.getId() == id);
        //TODO: Sql.remProduit(id);
        return res;
    }

    /**
     * Get all the distant cliories.
     * @return The ArrayList containing the cliories.
     */
    public ArrayList<Produit> getDistantProducts() {
        //TODO: clients = Sql.getCategories();
        return getProducts();
    }

    //TODO: Factorize
    /**
     * Get all the cliories.
     * @return The ArrayList containing the cliories.
     */
    public ArrayList<Produit> getProducts(){
        if (products == null)
            products = new ArrayList<Produit>();
        return products;
    }
}
