package tests.dao.ram;

import models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dao.ram.RAMClientDAO;

public class RAMClientDAOTest {

    @Test
    public void testSelectExist() {
        Client cli = new Client(0);
        RAMClientDAO.getInstance().create(cli);
        assertNotNull(RAMClientDAO.getInstance().getById(cli.getId()));
    }

    @Test
    public void testUpdate() {
        Client cli = new Client(0);
        RAMClientDAO.getInstance().create(cli);
        cli.setNom("nom");
        RAMClientDAO.getInstance().update(cli);
        assertEquals(RAMClientDAO.getInstance().getById(cli.getId()).getNom(), "nom");
    }

    @Test
    public void testDelete() {
        Client cli = new Client(0);
        RAMClientDAO.getInstance().create(cli);
        assertTrue(RAMClientDAO.getInstance().delete(cli));
    }

    @Test
    public void testGetAll() {
        Client cli = new Client(0);
        RAMClientDAO.getInstance().create(cli);
        assertFalse(RAMClientDAO.getInstance().getAll().isEmpty());
    }

}
