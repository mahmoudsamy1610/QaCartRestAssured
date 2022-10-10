package com.qacart.todo.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.qacart.todo.apis.UserAPIs;
import com.qacart.todo.data.ErrorsMessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.User;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;


@Feature("User Feature")
public class UserTests {



    @Story("should be able to add todo")
    @Test (description = "should be able to register")
    public void shouldBeAbleToRegister(){

        User user1 = UserSteps.GenerateUser();

       Response response =  UserAPIs.Register(user1);
        User RespUser = response.body().as(User.class);

                assertThat(response.statusCode() ,  equalTo(201)) ;
                assertThat(RespUser.getFirstName() , equalTo(user1.getFirstName()));

    }

    @Story("should NOT be able to register with already exists mail")
    @Test (description = "should NOT be able to register with already exists mail")
    public  void shouldNotBeAbleToRegister(){


                User user1 = UserSteps.GetRegisteredUser();

                Response response =  UserAPIs.Register(user1);

                Error returnError = response.body().as(Error.class);

                assertThat(response.statusCode() ,  equalTo(400)) ;
                assertThat(returnError.getMessage() , equalTo(ErrorsMessages.EmailAlreadyRegistered));


            }

    @Story("should be able to login")
    @Test (description = "should be able to login")
    public void shouldBeAbleToLogIn() {

        User user1 = UserSteps.GetRegisteredUser();

         String email = user1.getEmail();
         String password = user1.getPassword();
         String firstName = user1.getFirstName();

                User loginData= new User(email , password);
                Response response = UserAPIs.Login(loginData);

                User RespUser = response.body().as(User.class);

                assertThat(response.statusCode() , equalTo(200));
                assertThat(RespUser.getFirstName() , equalTo(firstName));
               assertThat(RespUser.getAccess_token() , not(equalTo(null)));
    }

    @Story("should NOT be able to log in with wrong password")
    @Test (description = "should NOT be able to log in with wrong password")
    public void shouldNOTBeAbleToLogInWithWrongPass() {

        User user1 = UserSteps.GetRegisteredUser();

        String email = user1.getEmail();
        String password = user1.getPassword();
        String firstName = user1.getFirstName();

        User loginData= new User(email , "wrong password");
        Response response = UserAPIs.Login(loginData);

        Error returnError = response.body().as(Error.class);

        assertThat(response.statusCode() ,  equalTo(401)) ;
        assertThat(returnError.getMessage() , equalTo(ErrorsMessages.EmailOrPasswordIsWrong));
    }


    }


