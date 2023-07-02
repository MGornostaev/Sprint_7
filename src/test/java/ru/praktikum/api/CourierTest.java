package ru.praktikum.api;

import ru.praktikum.api.courier.Courier;
import ru.praktikum.api.courier.CourierAssertions;
import ru.praktikum.api.courier.CourierClient;
import ru.praktikum.api.courier.CourierGenerator;

public class CourierTest {
    protected final CourierGenerator generator = new CourierGenerator();

    protected final CourierClient client = new CourierClient();

    protected final CourierAssertions check = new CourierAssertions();

    Courier courier = generator.random();
}
