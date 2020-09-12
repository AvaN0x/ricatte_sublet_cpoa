package controllers;

import java.util.ArrayList;

import models.Product;
import models.Category;

public class ProductController extends BaseController<Product> {

    public void addObject(Product prod) {
        super.addObject(prod);
        ProductSql.addObject(prod);
    }

    /**
     * Edit a product in the list.
     * 
     * @param prod           The product to edit.
     * @param newNom         The name of the product.
     * @param newDescription The description of the product.
     * @param newTarif       The price of the product.
     * @param newVisuel      The path to the image of the product.
     * @param newCategorie   The category of the product.
     * @return If the editing is successfull or not.
     */
    public boolean editObject(Product prod, String newNom, String newDescription, float newTarif, String newVisuel,
            Category newCategorie) throws InstantiationException, IllegalAccessException,
            java.lang.reflect.InvocationTargetException, SecurityException {
        return editObject(prod.getId(), newNom, newDescription, newTarif, newVisuel, newCategorie);
    }

    /**
     * Edit a product in the list.
     * 
     * @param id             The id of the product.
     * @param newNom         The name of the product.
     * @param newDescription The description of the product.
     * @param newTarif       The price of the product.
     * @param newVisuel      The path to the image of the product.
     * @param newCategorie   The category of the product.
     * @return If the editing is successfull or not.
     */
    public boolean editObject(int id, String newNom, String newDescription, float newTarif, String newVisuel,
            Category newCategorie) throws InstantiationException, IllegalAccessException,
            java.lang.reflect.InvocationTargetException, SecurityException {
        boolean isSucess = super.editObject(id, newNom, newDescription, newTarif, newVisuel, newCategorie);
        if (isSucess) {
            ProductSql.updateObject(id, newNom, newDescription, newTarif, newVisuel, newCategorie);
            return true;
        }
        return false;
    }

    public boolean removeObject(int id) {
        ProductSql.remObject(id);
        return super.removeObject(id);
    }

    public ArrayList<Product> getDistantObjects() {
        _objects = ProductSql.getObjects();
        return getObjects();
    }
}
