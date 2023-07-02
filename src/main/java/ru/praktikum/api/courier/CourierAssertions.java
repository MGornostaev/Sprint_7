package ru.praktikum.api.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierAssertions {
    @Step
    public int loggedInSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", notNullValue())
                .extract()
                .path("id");
    }
    @Step
    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("ok",equalTo(true));
    }

    @Step
    public void creationConflict(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .body("message",equalTo("Этот логин уже используется"));
    }

    @Step
    public void creationBadRequest(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step
    public void loginBadRequest(ValidatableResponse response) {
         response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message",equalTo("Недостаточно данных для входа"));
    }

    @Step
    public void loginNotFound(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_NOT_FOUND)
                .body("message",equalTo("Учетная запись не найдена"));
    }
}
