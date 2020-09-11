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
        Sql.addCategory(categ);
    }
    
    public void editCategory(Category categ, String newTitle, String newVisuel) {
        editCategory(categ.getId(), (newTitle != null ? newTitle : categ.getTitle()), (newVisuel != null ? newVisuel : categ.getVisuel()));
    }

    public void editCategory(int id, String newTitle, String newVisuel) {
        //TODO: Preconditions => if newTitle + newVisuel is null
        for (Category category : categories)
            if(category.getId() == id){
                if(newTitle == null)
                    newTitle = category.getTitle();
                    if(newVisuel == null)
                    newVisuel = category.getVisuel();

                category.setTitle(newTitle);
                category.setVisuel(newVisuel);
                Sql.updateCategory(id, newTitle, newVisuel);
            }
        //TODO: throw new CategoryNotFound
    }

    public void removeCategory(Category categ) {
        removeCategory(categ.getId());
    }

    public void removeCategory(int id) {
        categories.removeIf(categ -> categ.getId() == id);
        Sql.remCategory(id);
    }

    public ArrayList<Category> getCategories(){
        categories = Sql.getCategories();
        if(categories == null)
            categories = new ArrayList<Category>();
        return categories;
    }

}
