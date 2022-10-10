package com.qacart.todo.testcases;

import com.qacart.todo.apis.ToDoAPIs;
import com.qacart.todo.apis.UserAPIs;
import com.qacart.todo.data.ErrorsMessages;
import com.qacart.todo.models.ToDo;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.UserSteps;
import groovyjarjarantlr4.runtime.Token;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;

import static com.qacart.todo.steps.TodoSteps.generateTodo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

@Feature("TODO features")
public class TodoTests {


    @Story("should be able to add todo")
    @Test (description = "should be able to add todo")
    public void shouldBeAbleToAddTODO(){
        String Token = UserSteps.getUserToken();
        ToDo todo1 = generateTodo();

           Response response = ToDoAPIs.AddToDo(todo1, Token);

            ToDo RespTodo = response.body().as(ToDo.class);

                assertThat(response.statusCode(), equalTo(201));
                assertThat(RespTodo.getItem() ,equalTo(todo1.getItem()));
                assertThat(RespTodo.GetIsCompleted() ,equalTo(todo1.GetIsCompleted()));


    }

    @Story("should NOT be able to add todo without ic sompleted ")
    @Test (description = "should NOT be able to add todo without ic sompleted ")
    public void shouldNOTBeAbleToAddTODOWithoutIsCompleted(){

        String Token =UserSteps.getUserToken();
        ToDo todo1 = new ToDo( "TODO");

             Response response = ToDoAPIs.AddToDo(todo1, Token);

        Error RespError = response.body().as(Error.class);

                assertThat(response.statusCode(),equalTo(400));
                assertThat(RespError.getMessage() ,equalTo(ErrorsMessages.IsCompletedIsRequired));


    }

    @Story("should be able to get TODO by ID")
    @Test (description = "should be able to get TODO by ID")
    public  void ShouldBeAbleToGetTODOById(){
        String Token = UserSteps.getUserToken();
        ToDo todo = TodoSteps.generateTodo();
        String item = todo.getItem();
        String Id = TodoSteps.getTodoId(Token,todo) ;


        Response response = ToDoAPIs.GetToDo(Id ,Token);
        ToDo RespTodo = response.body().as(ToDo.class);


        assertThat(response.statusCode(), equalTo(200));
        assertThat(RespTodo.getItem(),equalTo(item));
        assertThat(RespTodo.GetIsCompleted() ,equalTo(false));



    }

    @Story("should be able to delete todo")
    @Test (description = "should be able to delete todo")
    public  void ShouldBeAbleToDeleteTodo(){
        String Token = UserSteps.getUserToken();
        ToDo todo = TodoSteps.generateTodo();
        String item = todo.getItem();
        String Id = TodoSteps.getTodoId(Token,todo) ;


        Response response = ToDoAPIs.DeleteTodo(Id ,Token);
        ToDo RespTodo = response.body().as(ToDo.class);


        assertThat(response.statusCode(), equalTo(200));
        assertThat(RespTodo.getItem(),equalTo(item));
        assertThat(RespTodo.GetIsCompleted() ,equalTo(false));



    }




}
