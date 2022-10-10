package com.qacart.todo.apis;

import com.qacart.todo.base.Specs;
import com.qacart.todo.data.Routes;
import com.qacart.todo.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPIs {

    public static Response Register(User user){

        Response response =
                given()
                        .spec(Specs.getRequestSpec())
                        .body(user)
                        .when().post(Routes.RegisterRoute)
                        .then()
                        .log().all()
                        .extract().response();

        return response;

    }


    public static Response Login(User user){


        Response  response =
                given()
                        .spec(Specs.getRequestSpec())
                        .body(user)
                        .when().post(Routes.LoginRoute)
                        .then()
                        .log().all()
                        .extract().response();

        return response;

    }


}
