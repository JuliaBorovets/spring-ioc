package com.softserve.itacademy.service;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;

import java.util.List;

public interface ToDoService {

    ToDo addTodo(ToDo todo, User user);

    ToDo updateTodo(Integer id, ToDo todo);

    void deleteTodo(ToDo todo);

    List<ToDo> getAll();

    List<ToDo> getByUser(User user);

    ToDo getByUserTitle(User user, String title);
    
}
