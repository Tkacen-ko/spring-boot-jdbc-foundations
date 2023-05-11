package ru.itsjava.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Import(UserRepositoryImpl.class)
public class UserRepositoryImplTest {
    public static final long DEFAULT_USER_ID = 5L;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldHaveCorrectGetById(){
        var expectedUser = entityManager.find(User.class, DEFAULT_USER_ID);
        var actualUser = userRepository.getById(DEFAULT_USER_ID);

        //System.out.println("Тестовые пользователи: "+expectedUser);
        //System.out.println("Актуальные пользователи: "+actualUser);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldHaveCorrectFindAllFilms(){
        var expectedFilms = entityManager
                .createQuery("select u from user u join u.pet", User.class)
                .getResultList();

        var actualFilms = userRepository.findAll();

        Assertions.assertEquals(expectedFilms, actualFilms);
    }
}
