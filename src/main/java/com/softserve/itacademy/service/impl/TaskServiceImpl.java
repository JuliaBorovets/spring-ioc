package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

    private ToDoService toDoService;
    private UserService userService;

    @Autowired
    public TaskServiceImpl(UserService userService, ToDoService toDoService) {
        this.userService = userService;
        this.toDoService = toDoService;
    }

    @Override
    public Task addTask(Task task, ToDo todo) {

        if (Objects.nonNull(task) && Objects.nonNull(todo)) {

            todo.getTasks().add(task);
            return task;
        } else {
            throw new IllegalArgumentException("Cannot be null arguments");
        }
    }

    @Override
    public Task updateTask(Integer id, Task task) {

        List<Task> tasks = getAll();

        if (Objects.nonNull(task)) {

            Task oldTask = getTaskById(id);
            int index = tasks.indexOf(oldTask);
            tasks.set(index, task);

            return tasks.get(index);
        } else {
            throw new IllegalArgumentException("Task cannot be null");
        }
    }

    @Override
    public Task getTaskById(Integer id) {
        return getAll().stream()
                .filter(i -> i.getTaskId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("can not find task with id=" + id));
    }

    @Override
    public void deleteTask(Task task) {
        toDoService.getAll()
                .forEach(i -> i.getTasks().remove(task));
    }

    @Override
    public List<Task> getAll() {
        return toDoService.getAll().stream()
                .map(ToDo::getTasks)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getByToDo(ToDo todo) {
        List<Task> tasks = new ArrayList<>();

        Optional.ofNullable(todo)
                .ifPresent(i -> tasks.addAll(i.getTasks()));

        return tasks;
    }

    @Override
    public Task getByToDoName(ToDo todo, String name) {
        if (Objects.nonNull(todo)) {
            return todo.getTasks()
                    .stream()
                    .filter(i -> name.equals(i.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cannot find task with a name=" + name));
        } else {
            throw new RuntimeException("ToDo cannot be null");
        }
    }

    @Override
    public Task getByUserName(User user, String name) {
        if (Objects.nonNull(user)) {
            return userService.getAll().stream()
                    .flatMap(user1 -> user1.getMyTodos().stream()
                            .flatMap(toDo -> toDo.getTasks().stream()))
                    .filter(task -> name.equals(task.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cannot find task with a name=" + name));
        } else {
            throw new RuntimeException("ToDo cannot be null");
        }
    }
}
