package tests.dao.ram;

import models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dao.ram.RAMCategoryDAO;

public class RAMCategoryDAOTest {

    @Test
    public void testSelectExist() {
        Category cli = new Category(0);
        RAMCategoryDAO.getInstance().create(cli);
        assertNotNull(RAMCategoryDAO.getInstance().getById(cli.getId()));
    }

    @Test
    public void testUpdate() {
        Category cli = new Category(0);
        RAMCategoryDAO.getInstance().create(cli);
        cli.setTitle("title");
        RAMCategoryDAO.getInstance().update(cli);
        assertEquals(RAMCategoryDAO.getInstance().getById(cli.getId()).getTitle(), "title");
    }

    @Test
    public void testDelete() {
        Category cli = new Category(0);
        RAMCategoryDAO.getInstance().create(cli);
        assertTrue(RAMCategoryDAO.getInstance().delete(cli));
    }

    @Test
    public void testGetAll() {
        Category cli = new Category(0);
        RAMCategoryDAO.getInstance().create(cli);
        assertFalse(RAMCategoryDAO.getInstance().getAll().isEmpty());
    }

}
