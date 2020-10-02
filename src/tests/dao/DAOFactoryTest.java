package tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.MySQL.*;
import dao.RAM.*;

public class DAOFactoryTest {

    @Test
    public void testGetMySQLDAOFactory() {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        assertEquals(MySQLDAOFactory.class, daos.getClass());
    }

    @Test
    public void testGetMySQLCategoryDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        assertEquals(MySQLCategoryDAO.class, daos.getCategoryDAO().getClass());
    }

    @Test
    public void testGetMySQLProductDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        assertEquals(MySQLProductDAO.class, daos.getProductDAO().getClass());
    }

    @Test
    public void testGetMySQLClientDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        assertEquals(MySQLClientDAO.class, daos.getClientDAO().getClass());
    }

    @Test
    public void testGetMySQLCommandDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
        assertEquals(MySQLCommandDAO.class, daos.getCommandDAO().getClass());
    }

    @Test
    public void testGetRAMDAOFactory() {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.RAM);
        assertEquals(RAMDAOFactory.class, daos.getClass());
    }

    @Test
    public void testGetRAMCategoryDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.RAM);
        assertEquals(RAMCategoryDAO.class, daos.getCategoryDAO().getClass());
    }

    @Test
    public void testGetRAMProductDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.RAM);
        assertEquals(RAMProductDAO.class, daos.getProductDAO().getClass());
    }

    @Test
    public void testGetRAMClientDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.RAM);
        assertEquals(RAMClientDAO.class, daos.getClientDAO().getClass());
    }

    @Test
    public void testGetRAMCommandDAO() throws Exception {
        var daos = dao.DAOFactory.getDAOFactory(dao.Persistance.RAM);
        assertEquals(RAMCommandDAO.class, daos.getCommandDAO().getClass());
    }

}
