package com.softserve.itacademy.service;

import com.softserve.itacademy.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(Integer id, User user);

    User getUserById(Integer id);

    void deleteUser(User user);

    List<User> getAll();
}
