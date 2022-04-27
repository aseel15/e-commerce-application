package com.example.assignment_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private Long customerId;

    private Date date;

    private double totalPrice;

    private String status;
}
