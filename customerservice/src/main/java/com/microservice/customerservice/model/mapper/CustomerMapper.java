package com.microservice.customerservice.model.mapper;


import com.microservice.customerservice.model.dtos.send.CustomerDtoSend;
import com.microservice.customerservice.model.dtos.save.CustomerDtoSave;
import com.microservice.customerservice.model.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDtoSave customerDtoSave);
    CustomerDtoSave toCustomerDtoSave(Customer customer);
    CustomerDtoSend toCustomerDtoSend(Customer customer);
}
