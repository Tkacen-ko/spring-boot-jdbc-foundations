package ru.itsjava.repository;

import ru.itsjava.domain.User;
import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User getById(long id);
}
