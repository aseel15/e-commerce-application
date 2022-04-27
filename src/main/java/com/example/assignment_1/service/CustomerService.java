package com.example.assignment_1.service;

import com.example.assignment_1.dto.CustomerDto;
import com.example.assignment_1.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    public List<CustomerDto> getAllCustomers();

    public CustomerDto getCustomerById(Long id);

    public CustomerDto addCustomer(CustomerDto customerDto);

    public CustomerDto updateCustomer(CustomerDto customerDto, Long id);

    public void deleteCustomer(Long id);
}
