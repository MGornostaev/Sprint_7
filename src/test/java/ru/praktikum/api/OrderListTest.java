package ru.praktikum.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderListTest extends OrderTest{

    @Before
    public void createOrder() {
        ValidatableResponse orderResponse = client.orderCreate(order);
        int track = check.createdSuccessfully(orderResponse);
        setTrack(track);
    }

    @Test
    @DisplayName("Order list request should return list of orders")
    public void orderListRequestShouldReturnListOfOrders() {
        ValidatableResponse orderResponse = client.orderList();

        check.gotListSuccessfully(orderResponse);
    }

    @After
    public void clean() {
        try {
            client.orderDelete(getTrack());
        } catch (AssertionError error) {
            System.out.println(error.getMessage());
        }
    }

}
