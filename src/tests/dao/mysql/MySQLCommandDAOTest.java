package tests.dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import models.Command;

public class MySQLCommandDAOTest {
    private dao.DAOFactory daos;
    private int com_id;

    @BeforeEach
    public void setUp() throws Exception {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        daos.getCommandDAO().create(new Command(LocalDate.now(), new models.Client(0)));
        var items = daos.getCommandDAO().getAll();
        this.com_id = items.get(items.size() - 1).getId();
    }

    @AfterEach
    public void tearUp() throws Exception {
        daos.getCommandDAO().delete(new Command(com_id));
    }

    @Test
    @Order(3)
    public void testGetById() throws Exception {
        assertNotNull(daos.getCommandDAO().getById(com_id));
    }

    @Test
    @Order(2)
    public void testCreate() throws Exception {
        var com = new Command(LocalDate.now(), new models.Client(0));
        daos.getCommandDAO().create(com);
        assertNotNull(daos.getCommandDAO().getById(com_id));
        daos.getCommandDAO().delete(com);
    }

    @Test
    @Order(4)
    public void testUpdate() throws Exception {
        var date = LocalDate.now();
        daos.getCommandDAO()
                .update(new Command(date, new models.Client(0)););
        assertEquals(date, daos.getCommandDAO().getById(com_id).getDateCommand());
    }

    @Test
    @Order(6)
    public void testDelete() throws Exception {
        var com = new Command(LocalDate.now(), new models.Client(0));
        daos.getCommandDAO().create(com);
        daos.getCommandDAO().delete(com);
        assertNull(daos.getCommandDAO().getById(com.getId()));
    }

    @Test
    @Order(5)
    public void testGetAll() throws Exception {
        assertFalse(daos.getCommandDAO().getAll().isEmpty());
    }

    @Test
    @Order(1)
    public void testGetInstance() throws Exception {
        assertEquals(dao.mysql.MySQLCommandDAO.class, daos.getCommandDAO().getClass());
    }
}
