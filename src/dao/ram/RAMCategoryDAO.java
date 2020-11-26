package dao.ram;

import java.util.ArrayList;

import dao.CategoryDAO;
import models.Category;

public class RAMCategoryDAO implements CategoryDAO {
    private static RAMCategoryDAO instance;

    private ArrayList<Category> data;

    private RAMCategoryDAO() {
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
    public boolean create(Category categ) throws IllegalArgumentException {
        while (data.contains(categ)) {
            if (data.get(categ.getId()).getTitle() == categ.getTitle())
                throw new IllegalAccessError("Duplicate key `titre`");
            categ.setId(categ.getId() + 1);
        }
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
        boolean isUsed;
        try {
            isUsed = RAMProductDAO.getInstance().getByCategory(categ).size() > 0;
        } catch (IllegalArgumentException e) {
            isUsed = false;
        }
        if (isUsed)
            throw new IllegalArgumentException("This category is used in products");
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

    public static RAMCategoryDAO getInstance() {
        if (instance == null)
            instance = new RAMCategoryDAO();

        return instance;
    }
}
