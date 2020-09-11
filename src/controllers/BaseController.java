package controllers;

import java.util.ArrayList;

import models.IBaseModel;

public abstract class BaseController<T extends IBaseModel> {
    protected ArrayList<T> objects;

     /**
     *  Add a new category to the list. The id is automaticly generated.
     * @param categ The category to add.
     */
    public abstract void addObject(T obj);

    /**
     * Remove a category in the list.
     * @param categ The category to remove.
     * @return If the removing is sucessfull or not.
     */
    public boolean removeCategory(T categ) {
        return removeCategory(categ.getId());
    }

    /**
     * Remove a category in the list.
     * @param id The id of the category
     * @return If the removing is sucessfull or not.
     */
    public abstract boolean removeCategory(int id);

    /**
     * Get all the distant categories.
     * @return The ArrayList containing the categories.
     */
    public abstract ArrayList<T> getDistantObjects();

    /**
     * Get all the categories.
     * @return The ArrayList containing the categories.
     */
    public ArrayList<T> getObjects(){
        if (objects == null)
            objects = new ArrayList<T>();
        return objects;
    }
}
