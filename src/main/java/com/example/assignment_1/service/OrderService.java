package com.example.assignment_1.service;

import com.example.assignment_1.dto.CustomerDto;
import com.example.assignment_1.dto.OrderDto;

import java.util.List;

public interface OrderService {
    public List<OrderDto> getAllOrders();

    public OrderDto getOrderById(Long id);

    public OrderDto addOrder(OrderDto orderDto);

    public OrderDto updateOrder(OrderDto orderDto, Long id);

    public void deleteOrder(Long id);
}
