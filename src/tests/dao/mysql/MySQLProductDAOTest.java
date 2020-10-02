package tests.dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import models.Product;

public class MySQLProductDAOTest {

    private dao.DAOFactory daos;
    private int prod_id;

    @BeforeEach
    public void setUp() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        var items = daos.getProductDAO().getAll();
        daos.getProductDAO().create(new Product("Test", "JUnit product", 0, "junit.png", new models.Category(0)));
        this.prod_id = items.get(items.size() - 1).getId();
    }

    @AfterEach
    public void tearUp() throws Exception {
        daos.getProductDAO().delete(new Product(prod_id));
    }

    @Test
    @Order(3)
    public void testGetById() throws Exception {
        assertNotNull(daos.getProductDAO().getById(prod_id));
    }

    @Test
    @Order(2)
    public void testCreate() throws Exception {
        var prod = new Product("TestCre", "JUnit product", 0, "junit.png", new models.Category(0));
        daos.getProductDAO().create(prod);
        assertNotNull(daos.getProductDAO().getById(prod_id));
        daos.getProductDAO().delete(prod);
    }

    @Test
    @Order(4)
    public void testUpdate() throws Exception {
        daos.getProductDAO()
                .update(new Product(prod_id, "MyTest", "JUnit product", 0, "junit.png", new models.Category(0)));
        assertEquals("MyTest", daos.getProductDAO().getById(prod_id).getNom());
    }

    @Test
    @Order(8)
    public void testDelete() throws Exception {
        var prod = new Product("TestDel", "JUnit product", 0, "junit.png", new models.Category(0));
        daos.getProductDAO().create(prod);
        daos.getProductDAO().delete(prod);
        assertNull(daos.getProductDAO().getById(prod.getId()));
    }

    @Test
    @Order(5)
    public void testGetByName() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        assertNotNull(daos.getProductDAO().getByName("Test"));
    }

    @Test
    @Order(6)
    public void testGetByCategory() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        var prods = daos.getProductDAO().getByCategory(new models.Category(0));
        if (prods.size() < 1)
            fail("getByCategory is empty");
    }

    @Test
    @Order(7)
    public void testGetAll() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        var prods = daos.getProductDAO().getAll();
        if (prods.size() < 1)
            fail("getAll is empty");
    }

    @Test
    @Order(1)
    public void testGetInstance() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        assertEquals(dao.mysql.MySQLProductDAO.class, daos.getProductDAO().getClass());
    }
}
