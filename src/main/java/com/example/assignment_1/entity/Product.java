package com.example.assignment_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private int size;

    @Column
    private int soldProduct;

    @Column
    private int remainedProduct;

    @Column
    private String color;

}
