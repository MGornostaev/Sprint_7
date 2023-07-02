package ru.praktikum.api.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.api.Client;

import static ru.praktikum.api.config.AppConfig.ORDER_API;

public class OrderClient extends Client {
    @Step
    public ValidatableResponse orderCreate(Order order) {
        return spec()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ORDER_API)
                .then().log().all();
    }

    @Step
    public void orderDelete(int id) {
        spec()
                .queryParam("track", id)
                .put(ORDER_API + ("/cancel"))
                .then().log().all();
    }

    @Step
    public ValidatableResponse orderList() {
        return spec()
                .get(ORDER_API)
                .then().log().all();
    }
}
