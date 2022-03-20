package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("select u from user u join u.pet", User.class)
                .getResultList();
    }
}
