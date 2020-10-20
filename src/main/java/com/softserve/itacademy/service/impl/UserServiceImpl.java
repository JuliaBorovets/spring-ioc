package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
    }

    @Override
    public User addUser(User user) {
        Optional.ofNullable(user).ifPresent(users::add);
        return user;
    }

    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = getUserById(id);
        int indexOfUser = users.indexOf(getUserById(id));

        if (users.contains(existingUser) && Objects.nonNull(user)) {
            users.set(indexOfUser, user);
            return users.get(indexOfUser);
        } else {
            return null;
        }
    }

    @Override
    public User getUserById(Integer id) {
        return users.stream().filter(user -> id.equals(user.getUserId())).findFirst().orElseThrow(() -> new RuntimeException("user with id " + id + " not found"));
    }

    @Override
    public void deleteUser(User user) {
        // TODO
    }

    @Override
    public List<User> getAll() {
        // TODO
        return null;
    }
}
