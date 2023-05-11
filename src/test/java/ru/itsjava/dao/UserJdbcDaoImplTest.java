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
@Import({UserDaoImpl.class, PetDaoImpl.class})
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "MrTester";
    private static final int DEFAULT_AGE = 55;
    private static final Pet DEFAULT_PET = new Pet(6L, "Test_Pet");

    @Autowired
    private UserDao userDao;

    @Autowired
    private PetDao petDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualUserCount = userDao.count();
        assertEquals(2, actualUserCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        petDao.insert(DEFAULT_PET);
        User expectedUser = new User(7L, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        long idFromDB = userDao.insert(expectedUser);
        User actualUser = userDao.findById(idFromDB);

        assertAll(() -> assertEquals(actualUser.getName(), expectedUser.getName()),
                () -> assertEquals(actualUser.getAge(), expectedUser.getAge()));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        petDao.insert(DEFAULT_PET);
        User expectedUser = new User(6L, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        userDao.update(expectedUser);
        User actualUser = userDao.findById(6L);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void shouldHaveCorrectDelete() {
        User deleteUser = userDao.findById(5L);
        userDao.delete(deleteUser);

        assertEquals(userDao.count(), 1);
    }
}
