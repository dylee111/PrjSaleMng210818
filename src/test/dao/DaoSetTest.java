package dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DaoSetTest {
    DaoSet daoSet = new DaoSet();

    @Test
    void connDB() {
        try {
            assertNotNull(daoSet.connDB());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}