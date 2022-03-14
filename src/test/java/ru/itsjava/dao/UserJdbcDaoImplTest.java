package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.dap.UserDao;
import ru.itsjava.dap.UserDaoImpl;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "MrTester";
    private static final int DEFAULT_AGE = 55;

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualUserCount = userDao.count();

        assertEquals(6, actualUserCount);
    }

    @Test
    public void shouldHaveCorrectIvsert() {
        User expectedUser = new User(7L, DEFAULT_NAME, DEFAULT_AGE);
        userDao.insert(expectedUser);
        User actualUser = userDao.findById(7L);

        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(1L, DEFAULT_NAME, DEFAULT_AGE);
        userDao.update(expectedUser);
        User actualUser = userDao.findById(1L);

        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void shouldHaveCorrectDelete() {
        User deleteUser = userDao.findById(1L);
        userDao.update(deleteUser);

        assertEquals(userDao.count(), 6);
    }
}
