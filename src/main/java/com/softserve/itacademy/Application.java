package com.softserve.itacademy;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);

        UserService userService = (UserService) annotationConfigContext.getBean("UserService");
        annotationConfigContext.close();

        System.out.println("User = " + userService.addUser(new User("FirstName", "LastName", "email@g", "password")));
        System.out.println("User = " + userService.addUser(new User("FirstName", "LastName", "email@g", "password")));

    }

}
