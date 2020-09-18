package dao;

import models.Category;

public interface CategoryDAO extends DAO<Category> {

    public abstract Category getByTitle(String title) throws Exception;
}
