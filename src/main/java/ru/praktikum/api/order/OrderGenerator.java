package ru.praktikum.api.order;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.concurrent.ThreadLocalRandom;

public class OrderGenerator {
    public Order generic() {
        return new Order("dsdsdsfds","sdsfdsfdsfd", "dfsdfdsfs","2","8989023452",2,"kmdskfdsf",new String[]{});
    }
    public Order random() {
        return new Order(RandomStringUtils.randomAlphanumeric(5,8)
                ,RandomStringUtils.randomAlphanumeric(5,8)
                ,RandomStringUtils.randomAlphanumeric(5,15),
                Integer.toString(ThreadLocalRandom.current().nextInt(1, 237)),
                RandomStringUtils.randomNumeric(10),
                ThreadLocalRandom.current().nextInt(1, 8),
                RandomStringUtils.randomAlphanumeric(5,15),
                new String[]{});
    }
}
