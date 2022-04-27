package com.example.assignment_1.service.serviceImp;

import com.example.assignment_1.dto.CustomerDto;
import com.example.assignment_1.entity.Customer;
import com.example.assignment_1.exception.ResourceNotFoundException;
import com.example.assignment_1.repository.CustomerRepository;
import com.example.assignment_1.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImp implements CustomerService {
    CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository){this.customerRepository = customerRepository;}

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(), "id", id));
        return mapToDto(customer);
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);
        return mapToDto(newCustomer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(),"id",id));
        customer.setId(customer.getId());
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);
        CustomerDto updatedCustomerDto = mapToDto(updatedCustomer);
        return updatedCustomerDto;
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(), "id", id));
        customerRepository.delete(customer);
    }

    private Customer mapToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());

        return customer;
    }
    private CustomerDto mapToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getAddress());
        return customerDto;

    }
}
