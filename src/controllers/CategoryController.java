package controllers;

import java.util.ArrayList;

import models.Category;

public class CategoryController extends BaseController<Category> {

    public void addObject(Category categ) {
        super.addObject(categ);
        CategorySql.addObject(categ);
    }

    /**
     * Edit a category in the list. The new title and visual can be null but not at
     * the same time.
     * 
     * @param categ     The category to edit.
     * @param newTitle  The new title of the category. Can be null.
     * @param newVisuel The new path to the image of the Category. Can be null.
     * @return If the editing is successfull or not.
     */
    public boolean editObject(Category categ, String newTitle, String newVisuel)
            throws IllegalArgumentException, InstantiationException, IllegalAccessException,
            java.lang.reflect.InvocationTargetException, SecurityException {
        return editObject(categ.getId(), newTitle, newVisuel);
    }

    /**
     * Edit a category in the list. The new title and visual can be null but not at
     * the same time.
     * 
     * @param id        The id of the category to edit.
     * @param newTitle  The new title of the category. Can be null.
     * @param newVisuel The new path to the image of the Category. Can be null.
     * @return If the editing is successfull or not.
     */
    public boolean editObject(int id, String newTitle, String newVisuel) throws InstantiationException,
            IllegalAccessException, java.lang.reflect.InvocationTargetException, SecurityException {
        boolean isSucess = super.editObject(id, newTitle, newVisuel);
        if (isSucess) {
            CategorySql.updateObject(id, newTitle, newVisuel);
            return true;
        }
        return false;
    }

    public boolean removeObject(int id) {
        CategorySql.remObject(id);
        return super.removeObject(id);
    }

    public ArrayList<Category> getDistantObjects() {
        _objects = CategorySql.getObjects();
        return getObjects();
    }

    public Category getObject(int id) {
        for (Category category : _objects)
            if (category.getId() == id) {
                return category;
            }
        return null;
    }
}
