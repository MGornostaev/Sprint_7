package ru.praktikum.api.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.*;

public class OrderAssertions {
    @Step
    public int createdSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track");
    }

    @Step
    public void gotListSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());
    }
}
