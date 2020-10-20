package com.softserve.itacademy;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(JUnitPlatform.class)
public class ToDoServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        annotationConfigContext.close();
    }

    @Test
    public void checkAddToDo() {
        User owner = new User("FirstName", "LastName", "email", "password");
        ToDo expected = new ToDo("title", LocalDateTime.now());

        ToDo actual = toDoService.addTodo(expected, owner);

        assertEquals(expected, actual);
        assertTrue(owner.getMyTodos().contains(actual));
        assertEquals(owner, actual.getOwner());
    }

    @Test
    public void shouldThrowExceptionCheckAddToDo() {
        User owner = new User("FirstName", "SecondName", "email", "password");
        ToDo expected = null;

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            ToDo actual = toDoService.addTodo(expected, owner);
        });

        assertEquals("Can not be null arguments", throwable.getMessage());
    }


    // TODO, other tests
}
