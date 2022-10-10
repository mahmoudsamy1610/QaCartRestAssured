package com.qacart.todo.apis;

import com.qacart.todo.base.Specs;
import com.qacart.todo.data.Routes;
import com.qacart.todo.models.ToDo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ToDoAPIs {

    public static Response AddToDo(ToDo todo, String token) {
        Response response =
                given()
                        .spec(Specs.getRequestSpec())
                        .body(todo)
                        .auth().oauth2(token)
                        .when()
                        .post(Routes.TodoRoute)
                        .then()
                        .log().all().extract().response();

        return  response;
    }

    public static  Response GetToDo(String Id , String token){

        Response response =
                given()
                        .spec(Specs.getRequestSpec())
                        .auth().oauth2(token)
                        .when()
                        .get(Routes.TodoRoute +"/" + Id )
                        .then()
                        .log().all().extract().response();

                return response;
    }


    public static  Response DeleteTodo(String Id , String token){

        Response response =
                given()
                        .spec(Specs.getRequestSpec())
                        .auth().oauth2(token)
                        .when()
                        .delete(Routes.TodoRoute + "/" + Id )
                        .then()
                        .log().all().extract().response();

        return response;
    }


}

