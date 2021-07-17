package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Customer;

import java.util.Collection;

public interface CustomerService {

    void add(String name, String additionalInformation);

    void delete(int id) throws WrongIdException;

    void update(String name, String additionalInformation, int id) throws WrongIdException;

    Collection<Customer> getCustomerList();

}
