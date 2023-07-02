package ru.praktikum.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.api.courier.*;

public class CourierLoginTest extends CourierTest{

    @Before
    public void createCourier() {
        client.courierCreate(courier);
    }

    @Test
    @DisplayName("Login with valid data should return 200 and courier id")
    public void courierLoginWithValidDataShouldReturn200Id() {
        Credentials credentials = Credentials.from(courier);
        ValidatableResponse loginResponse = client.courierLogIn(credentials);

        int id = check.loggedInSuccessfully(loginResponse);

        assert id > 0;
    }

    @Test
    @DisplayName("Login without login param should return 400 and error message")
    public void courierLoginWithoutLoginShouldReturn400BadRequest() {
        Credentials credentials = Credentials.from(courier);
        credentials.setLogin(null);
        ValidatableResponse loginResponse = client.courierLogIn(credentials);

        check.loginBadRequest(loginResponse);
    }

    @Test
    @DisplayName("Login without password param should return 400 and error message")
    public void courierLoginWithoutPasswordShouldReturn400BadRequest() {
        Credentials credentials = Credentials.from(courier);
        credentials.setPassword(null);
        ValidatableResponse loginResponse = client.courierLogIn(credentials);

        check.loginBadRequest(loginResponse);
    }

    @Test
    @DisplayName("Login with nonexistent data should return 404 and not found message")
    public void courierLoginWithNonexistentDataShouldReturn404NotFound() {
        Credentials credentials = Credentials.from(courier);
        credentials.setPassword(RandomStringUtils.randomAlphanumeric(5,8));
        credentials.setLogin(RandomStringUtils.randomAlphanumeric(5,8));
        ValidatableResponse loginResponse = client.courierLogIn(credentials);

        check.loginNotFound(loginResponse);
    }

    @Test
    @DisplayName("Login with invalid login should return 404 and not found message")
    public void courierLoginWithInvalidLoginShouldReturn404NotFound() {
        Credentials credentials = Credentials.from(courier);
        credentials.setLogin(RandomStringUtils.randomAlphanumeric(5,8));
        ValidatableResponse loginResponse = client.courierLogIn(credentials);

        check.loginNotFound(loginResponse);
    }

    @Test
    @DisplayName("Login with invalid password should return 404 and not found message")
    public void courierLoginWithInvalidPasswordShouldReturn404NotFound() {
        Credentials credentials = Credentials.from(courier);
        credentials.setPassword(RandomStringUtils.randomAlphanumeric(5,8));
        ValidatableResponse loginResponse = client.courierLogIn(credentials);

        check.loginNotFound(loginResponse);
    }

    @After
    public void clean() {
        Credentials credentials = Credentials.from(courier);
        ValidatableResponse loginResponse = client.courierLogIn(credentials);
        try {
            int id = check.loggedInSuccessfully(loginResponse);
            client.courierDelete(id);
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
    }

}
