package com.softserve.itacademy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

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


    // TODO, other tests
}
