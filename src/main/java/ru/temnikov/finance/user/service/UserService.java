package ru.temnikov.finance.user.service;

import ru.temnikov.finance.user.entity.User;

import java.util.List;

public interface UserService {

    boolean register(String username, String password);
    boolean login(String username, String password);
    User getActiveUser();
    List<User> getUsers();
    void setUsers(final List<User> users);
}
