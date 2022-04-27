package com.example.assignment_1.controller;

import com.example.assignment_1.dto.CustomerDto;
import com.example.assignment_1.exception.BadRequestException;
import com.example.assignment_1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/ecommerce/customers")
public class CustomerResource {
    @Autowired
    CustomerService customerService;

    public CustomerResource(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping
    public ResponseEntity <List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto){
        if (customerDto.getId() != null) {
            //log.error("Cannot have an ID {}", customerDto);
            throw new BadRequestException(CustomerResource.class.getSimpleName(),
                    "id");
        }
        return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("successfully deleted!", HttpStatus.OK);
    }
}
