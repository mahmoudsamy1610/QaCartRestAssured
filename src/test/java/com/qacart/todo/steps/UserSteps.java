package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.UserAPIs;
import com.qacart.todo.models.User;
import io.restassured.response.Response;

public class UserSteps {

    public static User GenerateUser(){
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "gogogogogo" ;

        User user = new User(firstName, lastName, email , password);

        return user ;

    }

    public static User GetRegisteredUser() {

        User user = GenerateUser();
        UserAPIs.Register(user);
        return user;
    }

    public static String getUserToken() {

        User user = GenerateUser();
        Response response = UserAPIs.Register(user);
       String ReturmResponse = response.body().path("access_token");

        return ReturmResponse;



    }


}
