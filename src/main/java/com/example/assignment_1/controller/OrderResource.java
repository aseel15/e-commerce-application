package com.example.assignment_1.controller;

import com.example.assignment_1.dto.OrderDto;
import com.example.assignment_1.exception.BadRequestException;
import com.example.assignment_1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/ecommerce/orders")
public class OrderResource {
    @Autowired
    OrderService orderService;

    public OrderResource(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    @PostMapping("/addOrder")
    public ResponseEntity<OrderDto> addOrder(@Valid @RequestBody OrderDto orderDto){
        if (orderDto.getId() != null) {
          //  log.error("Cannot have an ID {}", orderDto);
            throw new BadRequestException(OrderResource.class.getSimpleName(),
                    "Id");
        }
        return ResponseEntity.ok().body(orderService.addOrder(orderDto));
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(orderService.updateOrder(orderDto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("successfully deleted!");
    }
}
