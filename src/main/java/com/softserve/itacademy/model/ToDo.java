package com.softserve.itacademy.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ToDo {

    private static int counter = 0;

    private Integer toDoId;

    private String title;

    private LocalDateTime createdAt;

    private User owner;

    private List<Task> tasks;

    public ToDo(String title, LocalDateTime createdAt, User owner) {
        this.toDoId = counter++;
        this.title = title;
        this.createdAt = createdAt;
        this.owner = owner;
        this.tasks = new LinkedList<>();
    }

    public Integer getToDoId() {
        return toDoId;
    }

    public void setToDoId(Integer toDoId) {
        this.toDoId = toDoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo)) return false;

        ToDo toDo = (ToDo) o;

        if (!Objects.equals(toDoId, toDo.toDoId)) return false;
        if (!Objects.equals(title, toDo.title)) return false;
        if (!Objects.equals(createdAt, toDo.createdAt)) return false;
        if (!Objects.equals(owner, toDo.owner)) return false;
        return Objects.equals(tasks, toDo.tasks);
    }

    @Override
    public int hashCode() {
        int result = toDoId != null ? toDoId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ToDo{" + "toDoId=" + toDoId + ", title='" + title + ", createdAt=" + createdAt +
                ", owner=" + owner + ", tasks=" + tasks + '}';
    }
}
