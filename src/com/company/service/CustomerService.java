package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.CustomerDTO;

import java.util.Collection;

public interface CustomerService {

    void add(CustomerDTO customerDTO);

    void delete(int id) throws WrongIdException;

    void update(CustomerDTO customerDTO) throws WrongIdException;

    Collection<CustomerDTO> getCustomerList();

}
