package ru.praktikum.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderCreateTest extends OrderTest {

    private final String[] color;

    public OrderCreateTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {new String[] {"BLACK"}},
                {new String[] {"GREY"}},
                {new String[] {"BLACK", "GREY"}},
                {null},
        };
    }

    @Test
    @DisplayName("Create an order with a specific color choice")
    public void createOrderWithColorChoice() {
        order.setColor(color);
        ValidatableResponse orderResponse = client.orderCreate(order);

        int track = check.createdSuccessfully(orderResponse);
        setTrack(track);
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
