package com.example.assignment_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NonNull
    @Size(min = 3, max = 250)
    private String name;


    private String phoneNumber;

    private String address;
}
