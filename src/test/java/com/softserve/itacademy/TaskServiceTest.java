package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
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
    void updateTask() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void getAll() {
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
