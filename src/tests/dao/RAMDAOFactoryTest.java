package tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ram.*;

public class RAMDAOFactoryTest {

    private dao.DAOFactory daos;

    @BeforeEach
    public void setUp() {
        this.daos = dao.DAOFactory.getDAOFactory(dao.Persistance.RAM);
    }

    @Test
    public void testGetRAMDAOFactory() {
        assertEquals(RAMDAOFactory.class, daos.getClass());
    }

    @Test
    public void testGetRAMCategoryDAO() throws Exception {
        assertEquals(RAMCategoryDAO.class, daos.getCategoryDAO().getClass());
    }

    @Test
    public void testGetRAMProductDAO() throws Exception {
        assertEquals(RAMProductDAO.class, daos.getProductDAO().getClass());
    }

    @Test
    public void testGetRAMClientDAO() throws Exception {
        assertEquals(RAMClientDAO.class, daos.getClientDAO().getClass());
    }

    @Test
    public void testGetRAMCommandDAO() throws Exception {
        assertEquals(RAMCommandDAO.class, daos.getCommandDAO().getClass());
    }
}
