package ru.itsjava.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJdbcTest
@Import(UserRepositoryImpl.class)
public class UserRepositoryImplTest {
    public static final long DEFAULT_USER_ID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldHaveCorrectGetById(){
        var expectedUser = entityManager.find(User.class, DEFAULT_USER_ID);
        var actualUser = userRepository.getById(DEFAULT_USER_ID);

        Assertions.assertEquals(expectedUser, actualUser);
    }
}
