package com.microservice.customerservice.service.imp;

import com.microservice.customerservice.model.dtos.save.CustomerDtoSave;
import com.microservice.customerservice.model.dtos.send.CustomerDtoSend;
import com.microservice.customerservice.model.entities.Customer;
import com.microservice.customerservice.model.mapper.CustomerMapper;
import com.microservice.customerservice.repository.CustomerRepository;
import com.microservice.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public CustomerDtoSend save(CustomerDtoSave customerDtoSave) {
        return customerMapper.toCustomerDtoSend(
                customerRepository.save(
                        customerMapper.toCustomer(customerDtoSave)));
    }

    @Override
    public Optional<CustomerDtoSend> findById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return Optional.of(customerMapper.toCustomerDtoSend(customer));
    }
}
