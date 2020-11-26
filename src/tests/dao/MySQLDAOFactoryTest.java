package tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.mysql.*;

public class MySQLDAOFactoryTest {

    private dao.DAOFactory daos;

    @BeforeEach
    public void setUp() {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
    }

    @Test
    public void testGetMySQLDAOFactory() {
        assertEquals(MySQLDAOFactory.class, daos.getClass());
    }

    @Test
    public void testGetMySQLCategoryDAO() throws Exception {
        assertEquals(MySQLCategoryDAO.class, daos.getCategoryDAO().getClass());
    }

    @Test
    public void testGetMySQLProductDAO() throws Exception {
        assertEquals(MySQLProductDAO.class, daos.getProductDAO().getClass());
    }

    @Test
    public void testGetMySQLClientDAO() throws Exception {
        assertEquals(MySQLClientDAO.class, daos.getClientDAO().getClass());
    }

    @Test
    public void testGetMySQLCommandDAO() throws Exception {
        assertEquals(MySQLCommandDAO.class, daos.getCommandDAO().getClass());
    }

}
