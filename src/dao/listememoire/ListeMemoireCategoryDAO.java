package dao.listememoire;

import java.util.ArrayList;

import dao.CategoryDAO;
import models.Category;

public class ListeMemoireCategoryDAO implements CategoryDAO {
    private static ListeMemoireCategoryDAO instance;

    private ArrayList<Category> data;

    private ListeMemoireCategoryDAO() {
        this.data = new ArrayList<Category>();
    }

    @Override
    public Category getById(int id) throws IllegalArgumentException {
        int i = data.indexOf(new Category(id));
        if (i == -1)
            throw new IllegalArgumentException("No category have this id");
        return data.get(i);
    }

    @Override
    public boolean create(Category categ) {
        while (data.contains(categ))
            categ.setId(categ.getId() + 1);
        return data.add(categ);
    }

    @Override
    public boolean update(Category categ) throws IllegalArgumentException {
        int i = data.indexOf(categ);
        if (i == -1)
            throw new IllegalArgumentException("This category doesn't exist");
        data.set(i, categ);
        return true;
    }

    @Override
    public boolean delete(Category categ) throws IllegalArgumentException {
        int i = data.indexOf(categ);
        if (i == -1)
            throw new IllegalArgumentException("This category doesn't exist");
        return categ.equals(data.remove(i));
    }

    @Override
    public ArrayList<Category> getAll() {
        return data;
    }

    @Override
    public Category getByTitle(String title) throws IllegalArgumentException {
        for (Category categ : data)
            if (categ.getTitle() == title)
                return categ;
        throw new IllegalArgumentException("No category have this title");
    }

    public static ListeMemoireCategoryDAO getInstance() {
        if (instance == null)
            instance = new ListeMemoireCategoryDAO();

        return instance;
    }
}
