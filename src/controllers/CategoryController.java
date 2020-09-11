package controllers;

import java.util.ArrayList;

import models.Category;

public class CategoryController {
    private ArrayList<Category> categories = new ArrayList<Category>();

    public CategoryController() {
        getDistantCategories();
    }

    /**
     * Add a new category to the list. The id is automaticly generated.
     * @param title The title of the category.
     * @param visuel The path of the image of the category.
     */
    public void addCategory(String title, String visuel) {
        int id;
        if (categories.size() == 0)
            id = 1;
        else
            id = categories.get(categories.size() - 1).getId() + 1;
        addCategory(new Category(id, title, visuel));
    }

    /**
     *  Add a new category to the list. The id is automaticly generated.
     * @param categ The category to add.
     */
    public void addCategory(Category categ) {
        int lastId = categories.get(categories.size() - 1).getId();
        if (categ.getId() < lastId)
            categ.setId(lastId + 1);
        categories.add(categ);
        Sql.addCategory(categ);
    }

    /**
     * Edit a category in the list. The new title and visual can be null but not at the same time.
     * @param categ The category to edit. 
     * @param newTitle The new title of the category. Can be null.
     * @param newVisuel The new path to the image of the Category. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new title and visual are null.
     */
    public boolean editCategory(Category categ, String newTitle, String newVisuel) throws IllegalArgumentException {
        return editCategory(categ.getId(), newTitle, newVisuel);
    }

    /**
     * Edit a category in the list. The new title and visual can be null but not at the same time.
     * @param id The id of the category to edit.
     * @param newTitle The new title of the category. Can be null.
     * @param newVisuel The new path to the image of the Category. Can be null.
     * @return If the editing is successfull or not.
     * @throws IllegalArgumentException If the new title and visual are null.
     */
    public boolean editCategory(int id, String newTitle, String newVisuel) throws IllegalArgumentException {
        if (newTitle == null && newVisuel == null)
            throw new IllegalArgumentException();

        for (Category category : categories)
            if (category.getId() == id) {
                if (newTitle == null)
                    newTitle = category.getTitle();
                if (newVisuel == null)
                    newVisuel = category.getVisuel();

                category.setTitle(newTitle);
                category.setVisuel(newVisuel);
                Sql.updateCategory(id, newTitle, newVisuel);
                return true;
            }
        return false;
    }

    /**
     * Remove a category in the list.
     * @param categ The category to remove.
     * @return If the removing is sucessfull or not.
     */
    public boolean removeCategory(Category categ) {
        return removeCategory(categ.getId());
    }

    /**
     * Remove a category in the list.
     * @param id The id of the category
     * @return If the removing is sucessfull or not.
     */
    public boolean removeCategory(int id) {
        var res = categories.removeIf(categ -> categ.getId() == id);
        Sql.remCategory(id);
        return res;
    }

    /**
     * Get all the distant categories.
     * @return The ArrayList containing the categories.
     */
    public ArrayList<Category> getDistantCategories() {
        categories = Sql.getCategories();
        return getCategories();
    }

    /**
     * Get all the categories.
     * @return The ArrayList containing the categories.
     */
    public ArrayList<Category> getCategories(){
        if (categories == null)
            categories = new ArrayList<Category>();
        return categories;
    }

}
