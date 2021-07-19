package com.company.models.DTO.mapper;

import com.company.models.Customer;
import com.company.models.DTO.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
