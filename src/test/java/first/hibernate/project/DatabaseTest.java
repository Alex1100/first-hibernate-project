package first.hibernate.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {

    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }


    @Test
    public void test_openConnection() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:")) {
            assertTrue(connection.isValid(0), "true");
        }
    }

    @Test
    public void test_openSession_with_hibernate() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            assertTrue(session.isOpen(), "true");
            session.close();
        }
    }
}