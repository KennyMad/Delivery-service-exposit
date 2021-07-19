package com.company.models.DTO.mapper;

import com.company.models.Customer;
import com.company.models.DTO.CustomerDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-20T00:48:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setAdditionalInformation( customer.getAdditionalInformation() );

        return customerDTO;
    }

    @Override
    public Customer customerDTOtoCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setAdditionalInformation( customerDTO.getAdditionalInformation() );

        return customer;
    }
}
