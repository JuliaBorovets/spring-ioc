package com.softserve.itacademy.model;

import java.util.Objects;

public class Task {

    private static int counter = 0;

    private Integer taskId;

    private String name;

    private Priority priority;

    public Task(Integer taskId, String name, Priority priority) {
        this.taskId = counter++;
        this.name = name;
        this.priority = priority;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getTaskId(), task.getTaskId()) &&
                Objects.equals(getName(), task.getName()) &&
                getPriority() == task.getPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getName(), getPriority());
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
