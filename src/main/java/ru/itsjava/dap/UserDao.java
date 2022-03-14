package ru.itsjava.dap;

import ru.itsjava.domain.User;

public interface UserDao {

    int count();

    void insert(User user);

    void updateUser(User user);

    void delete(User user);

    User findById(Long id);

}
