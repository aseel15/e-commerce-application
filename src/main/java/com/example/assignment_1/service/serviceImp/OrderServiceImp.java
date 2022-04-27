package com.example.assignment_1.service.serviceImp;

import com.example.assignment_1.dto.CustomerDto;
import com.example.assignment_1.dto.OrderDto;
import com.example.assignment_1.entity.Customer;
import com.example.assignment_1.entity.Order;
import com.example.assignment_1.exception.ResourceNotFoundException;
import com.example.assignment_1.repository.CustomerRepository;
import com.example.assignment_1.repository.OrderRepository;
import com.example.assignment_1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImp implements OrderService {
   @Autowired
    OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(), "id", id));
        return mapToDto(order);
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = mapToEntity(orderDto);
        Order newOrder = orderRepository.save(order);
        return mapToDto(newOrder);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(),"id",id));
        order.setId(orderDto.getId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setDate(orderDto.getDate());
        order.setStatus(orderDto.getStatus());
        order.setTotalPrice(orderDto.getTotalPrice());
        Order updatedOrder = orderRepository.save(order);
        OrderDto updatedOrderDto = mapToDto(updatedOrder);
        return updatedOrderDto;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(), "id", id));
        orderRepository.delete(order);
    }

    private Order mapToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setStatus(orderDto.getStatus());
        order.setDate(orderDto.getDate());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }
    private OrderDto mapToDto(Order order){
        OrderDto orderDto = new OrderDto(order.getId(), order.getCustomerId(), order.getDate(), order.getTotalPrice(), order.getStatus());
        return orderDto;

    }
}
