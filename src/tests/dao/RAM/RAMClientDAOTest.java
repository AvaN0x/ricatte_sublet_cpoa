package tests.dao.RAM;

import models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dao.RAM.RAMClientDAO;

public class RAMClientDAOTest {

    @Test
    public void testSelectExist() {
        Client cli = new Client(0);
        RAMClientDAO.getInstance().create(cli);
        assertNotNull(RAMClientDAO.getInstance().getById(cli.getId()));
    }

}
