package controllers;

import java.util.ArrayList;

import models.Category;

public class CategoryController extends BaseController<Category> {
    public CategoryController() {
        // getDistantObjects();
    }

    /**
     * Add a new category to the list. The id is automaticly generated.
     * 
     * @param title  The title of the category.
     * @param visuel The path of the image of the category.
     */
    public void addObject(String title, String visuel) {
        int id;
        if (objects.size() == 0)
            id = 1;
        else
            id = objects.get(objects.size() - 1).getId() + 1;
        addObject(new Category(id, title, visuel));
    }

    public void addObject(Category categ) {
        int lastId = objects.get(objects.size() - 1).getId();
        if (categ.getId() < lastId)
            categ.setId(lastId + 1);
        objects.add(categ);
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
     * @throws IllegalArgumentException If the new title and visual are null.
     */
    public boolean editObject(Category categ, String newTitle, String newVisuel) throws IllegalArgumentException {
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
     * @throws IllegalArgumentException If the new title and visual are null.
     */
    public boolean editObject(int id, String newTitle, String newVisuel) throws IllegalArgumentException {
        if (newTitle == null && newVisuel == null)
            throw new IllegalArgumentException();

        for (Category category : objects)
            if (category.getId() == id) {
                if (newTitle == null)
                    newTitle = category.getTitle();
                if (newVisuel == null)
                    newVisuel = category.getVisuel();

                category.setTitle(newTitle);
                category.setVisuel(newVisuel);
                CategorySql.updateObject(id, newTitle, newVisuel);
                return true;
            }
        return false;
    }

    public boolean removeObject(int id) {
        var res = objects.removeIf(categ -> categ.getId() == id);
        CategorySql.remObject(id);
        return res;
    }

    public ArrayList<Category> getDistantObjects() {
        objects = CategorySql.getObjects();
        return getObjects();
    }

    public Category getObject(int id) {
        for (Category category : objects)
            if (category.getId() == id) {
                return category;
            }
        return null;
    }
}
