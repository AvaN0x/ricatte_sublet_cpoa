package tests.dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import models.Category;

public class MySQLCategoryDAOTest {
    private dao.DAOFactory daos;
    private int categ_id;

    @BeforeEach
    public void setUp() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        daos.getCategoryDAO().create(new Category("JUnitTest", "junit.png"));
        var items = daos.getCategoryDAO().getAll();
        this.categ_id = items.get(items.size() - 1).getId();
    }

    @AfterEach
    public void tearUp() throws Exception {
        daos.getCategoryDAO().delete(new Category(categ_id));
    }

    @Test
    @Order(3)
    public void testGetById() throws Exception {
        assertNotNull(daos.getCategoryDAO().getById(categ_id));
    }

    @Test
    @Order(2)
    public void testCreate() throws Exception {
        var cat = new Category("JUnitTest", "junit.png");
        daos.getCategoryDAO().create(cat);
        assertNotNull(daos.getCategoryDAO().getById(categ_id));
        daos.getCategoryDAO().delete(cat);
    }

    @Test
    @Order(4)
    public void testUpdate() throws Exception {
        daos.getCategoryDAO().update(new Category("JUnit", "junit.png"));
        assertEquals("JUnit", daos.getCategoryDAO().getById(categ_id).getTitle());
    }

    @Test
    @Order(7)
    public void testDelete() throws Exception {
        var cat = new Category("JUnitTest", "junit.png");
        daos.getCategoryDAO().create(cat);
        daos.getCategoryDAO().delete(cat);
        assertNull(daos.getCategoryDAO().getById(cat.getId()));
    }

    @Test
    @Order(5)
    public void testGetByName() throws Exception {
        assertNotNull(daos.getCategoryDAO().getByTitle("JUnitTest"));
    }

    @Test
    @Order(6)
    public void testGetAll() throws Exception {
        assertFalse(daos.getCategoryDAO().getAll().isEmpty());
    }

    @Test
    @Order(1)
    public void testGetInstance() throws Exception {
        assertEquals(dao.mysql.MySQLCategoryDAO.class, daos.getCategoryDAO().getClass());
    }
}
