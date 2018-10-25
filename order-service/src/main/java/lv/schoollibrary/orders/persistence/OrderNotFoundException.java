package lv.schoollibrary.orders.persistence;

import static java.lang.String.format;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String id) {
        super(format("Order %s not found", id));
    }
}
