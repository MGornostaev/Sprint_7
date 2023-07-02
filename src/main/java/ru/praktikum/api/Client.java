package ru.praktikum.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.praktikum.api.config.AppConfig.API_V1;
import static ru.praktikum.api.config.AppConfig.APP_URI;

public class Client {
    protected static RequestSpecification spec() {
        return given().log().all()
                .baseUri(APP_URI)
                .basePath(API_V1);
    }
}
