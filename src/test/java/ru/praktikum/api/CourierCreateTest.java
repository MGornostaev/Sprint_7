package ru.praktikum.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.api.courier.*;

public class CourierCreateTest extends CourierTest {

    @Test
    @DisplayName("Creation of a courier with unique valid data should return 201 and ok param with true")
    public void createCourierWithNewValidDataShouldReturn201True() {
        ValidatableResponse courierResponse = client.courierCreate(courier);

        check.createdSuccessfully(courierResponse);
    }

    @Test
    @DisplayName("Creation of a courier with existent data should return 409 and conflict message")
    public void createCourierWithExistentDataShouldReturn409Conflict() {
        client.courierCreate(courier);
        ValidatableResponse courierResponse = client.courierCreate(courier);

        check.creationConflict(courierResponse);
    }

    @Test
    @DisplayName("Creation of a courier with existent login should return 409 and conflict message")
    public void createCourierWithExistentLoginShouldReturn409Conflict() {
        client.courierCreate(courier);
        String firstCourierLogin = courier.getLogin();

        Courier secondCourier = generator.random();
        secondCourier.setLogin(firstCourierLogin);
        secondCourier.setPassword(RandomStringUtils.randomAlphanumeric(5,8));
        secondCourier.setFirstName(RandomStringUtils.randomAlphanumeric(5,8));

        ValidatableResponse courierResponse = client.courierCreate(secondCourier);

        check.creationConflict(courierResponse);
    }

    @Test
    @DisplayName("Creation of a courier without login should return 400 and error message")
    public void createCourierWithoutLoginShouldReturn400BadRequest() {
        courier.setLogin(null);
        ValidatableResponse courierResponse = client.courierCreate(courier);

        check.creationBadRequest(courierResponse);
    }

    @Test
    @DisplayName("Creation of a courier without password should return 400 and error message")
    public void createCourierWithoutPasswordShouldReturn400BadRequest() {
        courier.setPassword(null);
        ValidatableResponse courierResponse = client.courierCreate(courier);

        check.creationBadRequest(courierResponse);
    }

    @Test
    @DisplayName("Creation of a courier without first name should return 400 and error message")
    public void createCourierWithoutFirstNameShouldReturn201True() {
        courier.setFirstName(null);
        ValidatableResponse courierResponse = client.courierCreate(courier);

        check.createdSuccessfully(courierResponse);
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
