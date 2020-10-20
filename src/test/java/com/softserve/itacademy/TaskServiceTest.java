package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitPlatform.class)
public class TaskServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;
    private static TaskService taskService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        taskService = annotationConfigContext.getBean(TaskService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        annotationConfigContext.close();
    }

    @Test
    void checkAddTask() {
        ToDo todo = new ToDo("title1", LocalDateTime.now());

        Task expected = new Task("name1", Priority.LOW);
        Task actual = taskService.addTask(expected, todo);

        assertEquals(expected, actual);
        assertTrue(todo.getTasks().contains(actual));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionAddTest() {
        String expectedMessage = "Can not be null arguments";
        String actualMessage = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(null, null);
        }).getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void checkUpdateTask() {
        User owner = new User("FirstName", "LastName", "email", "password");

        ToDo toDo = new ToDo("title2", LocalDateTime.now());
        Task task1 = new Task("name2", Priority.LOW);
        Task task2 = new Task("name3", Priority.LOW);
        userService.addUser(owner);
        toDoService.addTodo(toDo, owner);

        List<Task> tasks = toDo.getTasks();
        tasks.add(task1);
        tasks.add(task2);

        Task expected = new Task("name4", Priority.HIGH);
        Task actual = taskService.updateTask(task2.getTaskId(), expected);

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionUpdateTest() {
        String expectedMessage = "Task cannot be null";

        String actualMessage = Assertions.assertThrows(RuntimeException.class, () -> {
            taskService.updateTask(1, null);
        }).getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getTaskById() {
        User owner = new User("FirstName1", "LastName", "email", "password");

        ToDo toDo = new ToDo("title3", LocalDateTime.now());
        Task task1 = new Task("name3", Priority.LOW);
        Task task2 = new Task("name4", Priority.LOW);
        userService.addUser(owner);
        toDoService.addTodo(toDo, owner);

        List<Task> tasks = toDo.getTasks();
        tasks.add(task1);
        tasks.add(task2);

        Task actual = taskService.getTaskById(task1.getTaskId());

        assertEquals(task1, actual);
    }

    @Test
    void deleteTask() {
        User user1 = new User("FirstName2", "LastName", "email", "password");
        ToDo toDo1 = new ToDo("title6", LocalDateTime.now());
        Task task1 = new Task("name7", Priority.MEDIUM);
        Task task2 = new Task("name8", Priority.MEDIUM);
        Task task3 = new Task("name9", Priority.MEDIUM);

        userService.addUser(user1);
        toDoService.addTodo(toDo1, user1);
        taskService.addTask(task1, toDo1);
        taskService.addTask(task2, toDo1);
        taskService.addTask(task3, toDo1);

        List<Task> tasks = taskService.getAll();
        int expect = tasks.size() - 1;
        taskService.deleteTask(task3);

        assertEquals(expect, taskService.getAll().size());
    }

    @Test
    void getAll() {
        User user1 = new User("FirstName2", "LastName", "email", "password");
        ToDo toDo1 = new ToDo("title4", LocalDateTime.now());
        Task task1 = new Task("name5", Priority.MEDIUM);

        User user2 = new User("FirstName3", "LastName", "email", "password");
        ToDo toDo2 = new ToDo("title5", LocalDateTime.now());
        Task task2 = new Task("name6", Priority.HIGH);

        userService.addUser(user1);
        userService.addUser(user2);
        toDoService.addTodo(toDo1, user1);
        toDoService.addTodo(toDo2, user2);
        taskService.addTask(task1, toDo1);
        taskService.addTask(task2, toDo2);

        List<Task> expected = new ArrayList<>();
        expected.add(task1);
        expected.add(task2);

        List<Task> actual = taskService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void getByToDo() {
    }

    @Test
    void getByToDoName() {
    }

    @Test
    void getByUserName() {
    }

    // TODO, other tests
}
