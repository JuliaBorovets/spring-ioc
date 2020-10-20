package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    private UserService userService;

    @Autowired
    public ToDoServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public ToDo addTodo(ToDo todo, User user) {

        if (todo != null && user != null) {
            todo.setOwner(user);
            user.getMyTodos().add(todo);
            return todo;
        } else {
            throw new IllegalArgumentException("Can not be null arguments");
        }
    }

    public ToDo updateTodo(ToDo todo) {
        // TODO
        return null;
    }

    public void deleteTodo(ToDo todo) {
        // TODO
    }

    public List<ToDo> getAll() {
        // TODO
        return null;
    }

    public List<ToDo> getByUser(User user) {
        // TODO
        return null;
    }

    public ToDo getByUserTitle(User user, String title) {
        // TODO
        return null;
    }

}
