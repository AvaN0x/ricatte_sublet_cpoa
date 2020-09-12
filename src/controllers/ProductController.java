package controllers;

import java.util.ArrayList;

import models.Product;
import models.Category;

public class ProductController extends BaseController<Product> {
    public ProductController() {
        // getDistantObjects();
    }

    /**
     * Add a new product to the list. The id is automaticly generated.
     * 
     * @param nom         The name of the product.
     * @param description The description of the product.
     * @param tarif       The price of the product.
     * @param visuel      The path to the image of the product.
     * @param categorie   The category of the product.
     */
    public void addObject(String nom, String description, float tarif, String visuel, Category categorie) {
        // TODO category as an int (id_categorie)
        int id;
        if (objects.size() == 0)
            id = 1;
        else
            id = objects.get(objects.size() - 1).getId() + 1;
        addObject(new Product(id, nom, description, tarif, visuel, categorie));
    }

    public void addObject(Product prod) {
        int lastId = objects.get(objects.size() - 1).getId();
        if (prod.getId() < lastId)
            prod.setId(lastId + 1);
        objects.add(prod);
        // TODO: Sql.addProduct(prod);
    }

    /**
     * Edit a product in the list. Except `prod`, all the parameters can be null but
     * not at the same time.
     * 
     * @param prod           The product to edit.
     * @param newNom         The name of the product. Can be null.
     * @param newDescription The description of the product. Can be null.
     * @param newTarif       The price of the product. Can be null.
     * @param newVisuel      The path to the image of the product. Can be null.
     * @param newCategorie   The category of the product. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If all of the optional parameters are null.
     */
    public boolean editProduct(Product prod, String newNom, String newDescription, float newTarif, String newVisuel,
            Category newCategorie) throws IllegalArgumentException {
        return editProduct(prod.getId(), newNom, newDescription, newTarif, newVisuel, newCategorie);
    }

    /**
     * Edit a product in the list. Except `id`, all the parameters can be null but
     * not at the same time.
     * 
     * @param id             The id of the product.
     * @param newNom         The name of the product. Can be null.
     * @param newDescription The description of the product. Can be null.
     * @param newTarif       The price of the product.
     * @param newVisuel      The path to the image of the product. Can be null.
     * @param newCategorie   The category of the product. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new name and first name are null.
     */
    public boolean editProduct(int id, String newNom, String newDescription, float newTarif, String newVisuel,
            Category newCategorie) throws IllegalArgumentException {
        if (newNom == null && newDescription == null && newVisuel == null && newCategorie == null)
            throw new IllegalArgumentException();

        for (Product product : objects)
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
                // TODO: Sql.updateProduct(id, newNom, newPrenom);
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
        var res = objects.removeIf(prod -> prod.getId() == id);
        // TODO: Sql.remProduct(id);
        return res;
    }

    /**
     * Get all the distant cliories.
     * 
     * @return The ArrayList containing the cliories.
     */
    public ArrayList<Product> getDistantObjects() {
        objects = ProductSql.getObjects();
        return getObjects();
    }
}
