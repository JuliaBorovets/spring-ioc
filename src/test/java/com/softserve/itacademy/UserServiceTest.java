package com.softserve.itacademy;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class UserServiceTest {
    private static UserService userService;

    private User user = new User("FirstName", "LastName", "email@gmail.com", "password");

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        annotationConfigContext.close();
    }

    @Test
    public void checkAddUser() {
        User expected = user;
        User actual = userService.addUser(user);
        //todo check with list (not implemented yet)
        Assertions.assertEquals(expected, actual, "user added");
    }

    @Test
    public void ShouldNotAddUser() {
        User expected = null;
        User actual = userService.addUser(null);
        //todo check with list (not implemented yet)
        Assertions.assertNull(actual, "user not added");
    }

    @Test
    void checkUpdateUser() {
        User user1 = userService.addUser(new User("firstName #1", "lastName #1", "email #1", "pass #1"));
        User user2 = userService.addUser(new User("firstName #2", "lastName #2", "email #2", "pass #2"));

        User expected = this.user;
        User actual = userService.updateUser(user1.getUserId(), this.user);

        Assertions.assertEquals(expected, actual, "user updated");
    }

    @Test
    void shouldThrowRuntimeExceptionWhenUserIdNotFound() {

        String expectedMessage = "user with id 1 not found";
        String actualMessage = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.updateUser(1, user);
        }).getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldNotUpdateNullUser() {
        User user1 = userService.addUser(new User("firstName #1", "lastName #1", "email #1", "pass #1"));
        User user2 = userService.addUser(new User("firstName #2", "lastName #2", "email #2", "pass #2"));

        User actual = userService.updateUser(user1.getUserId(), null);

        Assertions.assertNull(actual, "user not updated");
    }

    @Test
    void checkGetUserById() {
        User user1 = new User("firstName #1", "lastName #1", "email #1", "pass #1");
        User user2 = new User("firstName #2", "lastName #2", "email #2", "pass #2");
        userService.addUser(user1);
        userService.addUser(user2);

        User expected = user1;
        User actual = userService.getUserById(user1.getUserId());

        Assertions.assertEquals(user1, actual);
    }

    @Test
    void checkGetAll() {
        userService.addUser(user);
        userService.addUser(user);
        List<User> expected = new ArrayList<>();
        expected.add(user);
        expected.add(user);

        List<User> actual = userService.getAll();

        Assertions.assertEquals(expected, actual);
    }


    // TODO, other tests
}
