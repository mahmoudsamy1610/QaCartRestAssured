package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.ToDoAPIs;
import com.qacart.todo.apis.UserAPIs;
import com.qacart.todo.models.ToDo;
import com.qacart.todo.models.User;
import io.restassured.response.Response;

import static com.qacart.todo.steps.UserSteps.GenerateUser;

public class TodoSteps {

    public static ToDo generateTodo(){

        Faker faker = new Faker();
        String item = faker.book().title();
        Boolean isCompleted = false;

        ToDo todo = new ToDo(item,isCompleted);

        return todo;
    }


    public static String getTodoId (String Token, ToDo todo){

        String token = UserSteps.getUserToken();
        Response response = ToDoAPIs.AddToDo(todo,token);

        String ReturnResponse = response.body().path("_id");

        return  ReturnResponse;

    }


}
