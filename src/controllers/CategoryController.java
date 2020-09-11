package controllers;

import java.util.ArrayList;

import models.Category;

public class CategoryController {
    private ArrayList<Category> categories = new ArrayList<Category>();

    public CategoryController(){
        getCategories();
    }

    public void addCategory(String title, String visuel){
        int id;
        if(categories.size() == 0)
            id = 1;
        else
            id = categories.get(categories.size() - 1).getId() + 1;
        addCategory(new Category(id, title, visuel));
    }

    public void addCategory(Category categ){
        int lastId = categories.get(categories.size() - 1).getId()
        if(categ.getId() < lastId)
            categ.setId(lastId + 1);
        categories.add(categ);
        //TODO: SQL Insert
    }
    
    public void editCategory(Category categ, String newTitle, String newVisuel) {
        editCategory(categ.getId(), (newTitle != null ? newTitle : categ.getTitle()), (newVisuel != null ? newVisuel : categ.getVisuel()));
    }

    public void editCategory(int id, String newTitle, String newVisuel) {
        //TODO: Preconditions => if newTitle + newVisuel is null
        for (Category category : categories)
            if(category.getId() == id){
                if(newTitle != null)
                    category.setTitle(newTitle);
                if(newVisuel != null)
                    category.setVisuel(newVisuel);
            }
        //TODO: SQL Update
    }

    public void removeCategory(Category categ) {
        removeCategory(categ.getId());
    }

    public void removeCategory(int id) {
        categories.removeIf(categ -> categ.getId() == id);
        //TODO: SQL Delete
    }

    public ArrayList<Category> getCategories(){
        categories = new ArrayList<Category>();
        //TODO: SQL Select
        //TODO: SQL get all items and set it in categories
        return categories;
    }

}
