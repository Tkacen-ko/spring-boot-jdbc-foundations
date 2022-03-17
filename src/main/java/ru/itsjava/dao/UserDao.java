package ru.itsjava.dao;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserDao {

    int count();

    long insert(User user);

    void update(User user);

    void delete(User user);

    User findById(Long id);

    List<User> findAll();

}
