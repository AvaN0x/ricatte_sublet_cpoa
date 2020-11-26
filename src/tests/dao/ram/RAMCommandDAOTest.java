package tests.dao.ram;

import models.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dao.ram.RAMCommandDAO;

public class RAMCommandDAOTest {

    @Test
    public void testSelectExist() {
        Command cmd = new Command(0);
        RAMCommandDAO.getInstance().create(cmd);
        assertNotNull(RAMCommandDAO.getInstance().getById(cmd.getId()));
        RAMCommandDAO.getInstance().delete(cmd);
    }

    @Test
    public void testUpdate() {
        Command cmd = new Command(LocalDate.now(), new Client(-1));
        RAMCommandDAO.getInstance().create(cmd);
        cmd.setDateCommand(LocalDate.of(1970, 1, 1));
        RAMCommandDAO.getInstance().update(cmd);
        assertEquals(RAMCommandDAO.getInstance().getById(cmd.getId()).getDateCommand(), LocalDate.of(1970, 1, 1));
        RAMCommandDAO.getInstance().delete(cmd);
    }

    @Test
    public void testDeleteWork() {
        Command cmd = new Command(0);
        RAMCommandDAO.getInstance().create(cmd);
        assertTrue(RAMCommandDAO.getInstance().delete(cmd));
    }

    @Test
    public void testDeleteError() {
        Command cmd = new Command(-1);
        assertThrows(IllegalArgumentException.class, () -> {
            RAMCommandDAO.getInstance().delete(cmd);
        });
    }

    @Test
    public void testGetAllNotEmpty() {
        assertTrue(RAMCommandDAO.getInstance().getAll().isEmpty());
    }

    @Test
    public void testGetAllEmpty() {
        Command cmd = new Command(0);
        RAMCommandDAO.getInstance().create(cmd);
        assertFalse(RAMCommandDAO.getInstance().getAll().isEmpty());
        RAMCommandDAO.getInstance().delete(cmd);
    }

}
