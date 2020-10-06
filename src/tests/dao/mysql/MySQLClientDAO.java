package tests.dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import models.Client;

public class MySQLClientDAO {

    private dao.DAOFactory daos;
    private int client_id;

    @BeforeEach
    public void setUp() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        daos.getClientDAO()
                .create(new Client("JUnit-Test", "Client", "id", "mymdp", 0, "rue des lilas", 57000, "Metz", "France"));
        var items = daos.getClientDAO().getAll();
        this.client_id = items.get(items.size() - 1).getId();
    }

    @AfterEach
    public void tearUp() throws Exception {
        daos.getClientDAO().delete(new Client(client_id));
    }

    @Test
    @Order(3)
    public void testGetById() throws Exception {
        assertNotNull(daos.getClientDAO().getById(client_id));
    }

    @Test
    @Order(2)
    public void testCreate() throws Exception {
        var com = new Client("JUnit-Test", "MyClient", "id", "mymdp", 0, "avenue du g de gaulle", 57000, "Metz",
                "France");
        daos.getClientDAO().create(com);
        assertNotNull(daos.getClientDAO().getById(client_id));
        daos.getClientDAO().delete(com);
    }

    @Test
    @Order(4)
    public void testUpdate() throws Exception {
        daos.getClientDAO().update(
                new Client("JUnit", "MyClient", "id", "mymdp", 0, "avenue du g de gaulle", 57000, "Metz", "France"));
        assertEquals("Junit", daos.getClientDAO().getById(client_id).getNom());
    }

    @Test
    @Order(6)
    public void testDelete() throws Exception {
        var com = new Client("JUnit-Test", "MyClient", "id", "mymdp", 0, "avenue du g de gaulle", 57000, "Metz",
                "France");
        daos.getClientDAO().create(com);
        daos.getClientDAO().delete(com);
        assertNull(daos.getClientDAO().getById(com.getId()));
    }

    @Test
    @Order(5)
    public void testGetAll() throws Exception {
        assertFalse(daos.getClientDAO().getAll().isEmpty());
    }

    @Test
    @Order(1)
    public void testGetInstance() throws Exception {
        assertEquals(dao.mysql.MySQLClientDAO.class, daos.getClientDAO().getClass());
    }
}
