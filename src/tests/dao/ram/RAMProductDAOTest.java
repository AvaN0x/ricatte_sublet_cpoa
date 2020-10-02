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
        RAMProductDAO.getInstance().delete(prod);
    }

    @Test
    public void testUpdate() {
        Product prod = new Product(0);
        RAMProductDAO.getInstance().create(prod);
        prod.setNom("nom");
        RAMProductDAO.getInstance().update(prod);
        assertEquals(RAMProductDAO.getInstance().getById(prod.getId()).getNom(), "nom");
        RAMProductDAO.getInstance().delete(prod);
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
    public void testGetAllNotEmpty() {
        assertTrue(RAMProductDAO.getInstance().getAll().isEmpty());
    }

    @Test
    public void testGetAllEmpty() {
        Product prod = new Product(0);
        RAMProductDAO.getInstance().create(prod);
        assertFalse(RAMProductDAO.getInstance().getAll().isEmpty());
        RAMProductDAO.getInstance().delete(prod);
    }

    @Test
    public void testGetByTitle() {
        Product prod = new Product(0, "MyTestingProductName", "", (float) 0.0, "", new Category(0));
        RAMProductDAO.getInstance().create(prod);
        assertNotNull(RAMProductDAO.getInstance().getByName(prod.getNom()));
        RAMProductDAO.getInstance().delete(prod);
    }

    @Test
    public void testGetByTitleError() {
        assertThrows(IllegalArgumentException.class, () -> {
            RAMProductDAO.getInstance().getByName("ThisNameDoesNotExist");
        });
    }

    // @Test
    // public void testGetByCategory() {
    // Category categ = new Category(-1);
    // Product prod = new Product(-1, "MyTestingProductName", "", (float) 0.0, "",
    // categ);
    // RAMProductDAO.getInstance().create(prod);
    // assertFalse(RAMProductDAO.getInstance().getByCategory(categ).isEmpty());
    // RAMProductDAO.getInstance().delete(prod);
    // }

    // @Test
    // public void testGetByCategoryError() {
    // assertThrows(IllegalArgumentException.class, () -> {
    // RAMProductDAO.getInstance().getByCategory(new Category(-1));
    // });
    // }

}
