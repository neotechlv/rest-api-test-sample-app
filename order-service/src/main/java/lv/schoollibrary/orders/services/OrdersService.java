package lv.schoollibrary.orders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lv.schoollibrary.orders.domain.EntityMapper;
import lv.schoollibrary.orders.domain.Order;
import lv.schoollibrary.orders.dtos.OrderInfoDto;
import lv.schoollibrary.orders.dtos.OrderRequestDto;
import lv.schoollibrary.orders.persistence.OrderNotFoundException;
import lv.schoollibrary.orders.persistence.OrderRepository;

@Service
public class OrdersService {

    private final OrderRepository orderRepository;

    private final EntityMapper entityMapper;

    @Autowired
    public OrdersService(OrderRepository orderRepository, EntityMapper entityMapper) {
        this.orderRepository = orderRepository;
        this.entityMapper = entityMapper;
    }

    public OrderInfoDto addItem(OrderRequestDto item) {
        Order order = entityMapper.fromRequestDto(item);
        Order saved = orderRepository.save(order);
        return entityMapper.toResponseDto(saved);
    }

    public void deleteItem(String id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }

    public OrderInfoDto updateItem(String id, OrderRequestDto item) {
        Order order = findById(id);
        order.setBookId(item.getBookId());
        order.setSchoolKidId(item.getSchoolKidId());
        order.setTakenUntil(item.getTakenUntil());
        Order saved = orderRepository.save(order);
        return entityMapper.toResponseDto(saved);
    }

    public List<OrderInfoDto> allKidsOrders(String schoolKidId) {
        return orderRepository.findBySchoolKidId(schoolKidId).stream()
                .map(entityMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<OrderInfoDto> allOrders() {
        return orderRepository.findAll().stream()
                .map(entityMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public OrderInfoDto getById(String orderId) {
        return entityMapper.toResponseDto(findById(orderId));
    }

    private Order findById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

}
