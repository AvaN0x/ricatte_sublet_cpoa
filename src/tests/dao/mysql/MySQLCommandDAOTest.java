package tests.dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

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
        daos.getCommandDAO().create(new Command(java.time.LocalDate.now(), new models.Client(0)));
        var items = daos.getCommandDAO().getAll();
        this.com_id = items.get(items.size() - 1).getId();
    }

    @AfterEach
    public void tearUp() throws Exception {
        daos.getCommandDAO().delete(new Command(com_id));
    }
}
