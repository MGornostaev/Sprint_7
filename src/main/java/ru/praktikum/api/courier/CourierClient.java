package ru.praktikum.api.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.api.Client;

import static ru.praktikum.api.config.AppConfig.COURIER_API;

public class CourierClient extends Client {
    @Step
    public void courierDelete(int id) {
        spec()
                .delete(COURIER_API + String.format("/%d", id))
                .then().log().all();
    }

    @Step
    public ValidatableResponse courierCreate(Courier courier) {
        return spec()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_API)
                .then().log().all();
    }

    @Step
    public ValidatableResponse courierLogIn(Credentials credentials) {
        return spec()
                .contentType(ContentType.JSON)
                .body(credentials)
                .post(COURIER_API + "/login")
                .then().log().all();
    }
}
