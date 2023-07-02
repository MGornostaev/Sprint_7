package ru.praktikum.api.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier generic() {
        return new Courier("mishka001","22221","sash");
    }

    public Courier random() {
        return new Courier(RandomStringUtils.randomAlphanumeric(5,15)
                ,RandomStringUtils.randomAlphanumeric(4,8)
                ,RandomStringUtils.randomAlphanumeric(5,8));
    }
}
