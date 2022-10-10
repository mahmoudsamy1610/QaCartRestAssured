package com.qacart.todo.base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specs {

    public  static String  getEnv() {

       String Env =  System.getProperty("Env" , "Live");
       String baseUrl ;
        switch (Env){
            case "Live":
                baseUrl = "https://qacart-todo.herokuapp.com";
                break;
            case "Dev" :
                baseUrl = "https://localhost:8080";
                break;
            default :
                throw new RuntimeException("Environment not supported");
        }

        return  baseUrl ;

    }

    public static RequestSpecification getRequestSpec(){

        RequestSpecification reqSpec =
                given().baseUri(getEnv())
                .contentType(ContentType.JSON)
                        .log().all() ;

        return reqSpec;


    }



}
