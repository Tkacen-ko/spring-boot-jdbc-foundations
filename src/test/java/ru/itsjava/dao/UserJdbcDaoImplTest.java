package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "MrTester";
    private static final int DEFAULT_AGE = 55;
    private static final Pet DEFAULT_PET = new Pet(1L, "Bolonka");

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualUserCount = userDao.count();

        assertEquals(6, actualUserCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        User expectedUser = new User(7L, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        long idFromDB = userDao.insert(expectedUser);
        User actualUser = userDao.findById(idFromDB);

        assertAll(() -> assertEquals(actualUser.getName(), expectedUser.getName()),
                () -> assertEquals(actualUser.getAge(), expectedUser.getAge()));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(1L, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
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
