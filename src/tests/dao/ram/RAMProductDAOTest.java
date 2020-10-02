package tests.dao.ram;

import models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dao.ram.RAMProductDAO;

public class RAMProductDAOTest {

    @Test
    public void testSelectExist() {
        Product prod = new Product(0);
        RAMProductDAO.getInstance().create(prod);
        assertNotNull(RAMProductDAO.getInstance().getById(prod.getId()));
    }

    @Test
    public void testUpdate() {
        Product prod = new Product(0);
        RAMProductDAO.getInstance().create(prod);
        prod.setNom("nom");
        RAMProductDAO.getInstance().update(prod);
        assertEquals(RAMProductDAO.getInstance().getById(prod.getId()).getNom(), "nom");
    }

    @Test
    public void testDeleteWork() {
        Product prod = new Product(0);
        RAMProductDAO.getInstance().create(prod);
        assertTrue(RAMProductDAO.getInstance().delete(prod));
    }

    @Test
    public void testDeleteError() {
        Product prod = new Product(-1);
        assertThrows(IllegalArgumentException.class, () -> {
            RAMProductDAO.getInstance().delete(prod);
        });
    }

    @Test
    public void testGetAll() {
        Product prod = new Product(0);
        RAMProductDAO.getInstance().create(prod);
        assertFalse(RAMProductDAO.getInstance().getAll().isEmpty());
    }

    @Test
    public void testGetByTitle() {
        Product prod = new Product(0, "MyTestingProductName", "", (float) 0.0, "", new Category(0));
        RAMProductDAO.getInstance().create(prod);
        assertNotNull(RAMProductDAO.getInstance().getByName(prod.getNom()));
    }

    @Test
    public void testGetByTitleError() {
        assertThrows(IllegalArgumentException.class, () -> {
            RAMProductDAO.getInstance().getByName("ThisNameDoesNotExist");
        });
    }

    // TODO getByCategory
}
