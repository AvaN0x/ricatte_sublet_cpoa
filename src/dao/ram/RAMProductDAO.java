package dao.ram;

import java.util.ArrayList;

import dao.ProductDAO;
import models.Category;
import models.Product;

public class RAMProductDAO implements ProductDAO {
    private static RAMProductDAO instance;

    private ArrayList<Product> data;

    private RAMProductDAO() {
        this.data = new ArrayList<Product>();
    }

    @Override
    public Product getById(int id) throws IllegalArgumentException {
        int i = data.indexOf(new Product(id));
        if (i == -1)
            throw new IllegalArgumentException("No product have this id");
        return data.get(i);
    }

    @Override
    public boolean create(Product prod) {
        while (data.contains(prod)) {
            if (data.get(prod.getId()).getNom().equals(prod.getNom())
                    && data.get(prod.getId()).getCategory().getId() == prod.getCategory().getId())
                throw new IllegalAccessError("Duplicate key `nom` and `id_category`");
            prod.setId(prod.getId() + 1);
        }
        return data.add(prod);
    }

    @Override
    public boolean update(Product prod) throws IllegalArgumentException {
        int i = data.indexOf(prod);
        if (i == -1)
            throw new IllegalArgumentException("This product doesn't exist");
        data.set(i, prod);
        return true;
    }

    @Override
    public boolean delete(Product prod) throws IllegalArgumentException {
        int i = data.indexOf(prod);
        if (i == -1)
            throw new IllegalArgumentException("This product doesn't exist");
        var cmds = RAMCommandDAO.getInstance().getAll();
        for (var cmd : cmds) {
            if (cmd.getCommandLines().containsKey(prod))
                throw new IllegalArgumentException("This category is used in products");
        }
        return prod.equals(data.remove(i));
    }

    @Override
    public ArrayList<Product> getAll() {
        return data;
    }

    @Override
    public Product getByName(String name) throws IllegalArgumentException {
        for (Product prod : data)
            if (prod.getNom() == name)
                return prod;
        throw new IllegalArgumentException("No product have this name");
    }

    @Override
    public ArrayList<Product> getByCategory(Category categ) throws IllegalArgumentException {
        ArrayList<Product> prods = new ArrayList<Product>();

        for (Product prod : data)
            if (prod.getCategory().equals(categ))
                prods.add(prod);
        if (prods.size() > 0)
            return prods;
        throw new IllegalArgumentException("No product have this category");
    }

    public static RAMProductDAO getInstance() {
        if (instance == null)
            instance = new RAMProductDAO();

        return instance;
    }
}
