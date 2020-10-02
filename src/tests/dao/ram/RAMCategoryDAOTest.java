package tests.dao.ram;

import models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dao.ram.RAMCategoryDAO;

public class RAMCategoryDAOTest {

    @Test
    public void testSelectExist() {
        Category categ = new Category(0);
        RAMCategoryDAO.getInstance().create(categ);
        assertNotNull(RAMCategoryDAO.getInstance().getById(categ.getId()));
    }

    @Test
    public void testUpdate() {
        Category categ = new Category(0);
        RAMCategoryDAO.getInstance().create(categ);
        categ.setTitle("title");
        RAMCategoryDAO.getInstance().update(categ);
        assertEquals(RAMCategoryDAO.getInstance().getById(categ.getId()).getTitle(), "title");
    }

    @Test
    public void testDeleteWork() {
        Category categ = new Category(0);
        RAMCategoryDAO.getInstance().create(categ);
        assertTrue(RAMCategoryDAO.getInstance().delete(categ));
    }

    @Test
    public void testDeleteError() {
        Category categ = new Category(-1);
        assertThrows(IllegalArgumentException.class, () -> {
            RAMCategoryDAO.getInstance().delete(categ);
        });
    }

    @Test
    public void testGetAll() {
        Category categ = new Category(0);
        RAMCategoryDAO.getInstance().create(categ);
        assertFalse(RAMCategoryDAO.getInstance().getAll().isEmpty());
    }

    @Test
    public void testGetByTitle() {
        Category categ = new Category(0, "MyTestingCategoryName", "");
        RAMCategoryDAO.getInstance().create(categ);
        assertNotNull(RAMCategoryDAO.getInstance().getByTitle(categ.getTitle()));
    }

}
