package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

    private ToDoService toDoService;

    @Autowired
    public TaskServiceImpl(ToDoService toDoService) {
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
        // TODO
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
        // TODO
        return null;
    }

    @Override
    public Task getByToDoName(ToDo todo, String name) {
        // TODO
        return null;
    }

    @Override
    public Task getByUserName(User user, String name) {
        // TODO
        return null;
    }
}
