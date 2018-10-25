package lv.schoollibrary.orders.domain;

import lv.schoollibrary.orders.dtos.OrderInfoDto;

public class EntityFactory {

    public OrderInfoDto createOrderResponseDto() {
        return new OrderInfoDto();
    }

    public Order createOrder() {
        return new Order();
    }
}
