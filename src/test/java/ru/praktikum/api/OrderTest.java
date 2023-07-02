package ru.praktikum.api;

import ru.praktikum.api.order.Order;
import ru.praktikum.api.order.OrderAssertions;
import ru.praktikum.api.order.OrderClient;
import ru.praktikum.api.order.OrderGenerator;

public class OrderTest {
    protected final OrderGenerator generator = new OrderGenerator();

    protected final OrderClient client = new OrderClient();

    protected final OrderAssertions check = new OrderAssertions();

    private int track;

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    Order order = generator.random();
}
