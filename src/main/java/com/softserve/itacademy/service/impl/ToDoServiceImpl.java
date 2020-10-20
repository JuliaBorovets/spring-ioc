package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public ToDo updateTodo(Integer id, ToDo todo) {

        ToDo updatedToDo = getToDoById(id);

        updatedToDo.setOwner(todo.getOwner());
        updatedToDo.setCreatedAt(todo.getCreatedAt());
        updatedToDo.setTasks(todo.getTasks());
        updatedToDo.setTitle(todo.getTitle());

        return updatedToDo;
    }

    private ToDo getToDoById(Integer id) {
        return getAll().stream()
                .filter(i -> i.getToDoId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("can not find ToDo with id=" + id));
    }

    public void deleteTodo(ToDo todo) {
        userService.getAll()
                .forEach(i -> i.getMyTodos().remove(todo));
    }

    public List<ToDo> getAll() {
        return userService.getAll()
                .stream()
                .map(User::getMyTodos)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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
