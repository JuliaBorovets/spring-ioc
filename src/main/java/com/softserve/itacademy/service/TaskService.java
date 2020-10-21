package com.softserve.itacademy.service;

import java.util.List;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;

public interface TaskService {
    
    Task addTask(Task task, ToDo todo);

    Task updateTask(Integer id, Task task);

    Task getTaskById(Integer id);

    void deleteTask(Task task);

    List<Task> getAll();

    List<Task> getByToDo(ToDo todo);

    Task getByToDoName(ToDo todo, String name);

    Task getByUserName(User user, String name);
}
