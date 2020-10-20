package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
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
import java.util.Arrays;
import java.util.List;

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

    @Test
    void checkGetAll() {

        User user1 = new User("FirstName1", "LastName1", "email1", "password1");
        ToDo toDo1 = new ToDo("title1", LocalDateTime.now());

        User user2 = new User("FirstName2", "LastName2", "email2", "password2");
        ToDo toDo2 = new ToDo("title2", LocalDateTime.now());

        userService.addUser(user1);
        userService.addUser(user2);
        toDoService.addTodo(toDo1, user1);
        toDoService.addTodo(toDo2, user2);

        List<ToDo> expected = Arrays.asList(toDo1, toDo2);
        List<ToDo> actual = toDoService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void checkUpdateToDo() {

        User oldOwner = new User("FirstName1", "LastName1", "email1", "password1");
        ToDo oldToDo = new ToDo("title edited", LocalDateTime.now().minusDays(1));
        userService.addUser(oldOwner);
        toDoService.addTodo(oldToDo, oldOwner);

        ToDo expectedToDo = new ToDo("title edited", LocalDateTime.now());
        expectedToDo.getTasks().add(new Task("name", Priority.HIGH));

        User expectedOwner = new User("FirstName2", "LastName2", "email2", "password2");
        expectedToDo.setOwner(expectedOwner);

        ToDo actual = toDoService.updateTodo(oldToDo.getToDoId(), expectedToDo);

        assertEquals(oldToDo.getToDoId(), actual.getToDoId());
        assertEquals(expectedOwner, actual.getOwner());
        assertEquals(expectedToDo.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expectedToDo.getTasks(), actual.getTasks());
        assertEquals(expectedToDo.getTitle(), actual.getTitle());
    }

    @Test
    void shouldThrowExceptionUpdateToDo() {

        ToDo expectedToDo = new ToDo("title edited", LocalDateTime.now());
        expectedToDo.getTasks().add(new Task("name", Priority.HIGH));

        User expectedOwner = new User("FirstName2", "LastName2", "email2", "password2");
        expectedToDo.setOwner(expectedOwner);

        Throwable throwable = assertThrows(RuntimeException.class, () -> {
            ToDo actual = toDoService.updateTodo(-1, expectedToDo);
        });

        assertEquals("can not find ToDo with id=-1", throwable.getMessage());
    }


    // TODO, other tests
}
