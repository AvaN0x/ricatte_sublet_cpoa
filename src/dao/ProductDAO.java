package dao;

import java.util.ArrayList;

import models.Category;
import models.Product;

public interface ProductDAO extends DAO<Product> {
    public abstract Product getByName(String name) throws Exception;

    public abstract ArrayList<Product> getByCategory(Category categ) throws Exception;
}
