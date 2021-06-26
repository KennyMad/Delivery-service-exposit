package com.company.service;

import com.company.exception.WrongIdException;
import com.company.models.Customer;

import java.util.Collection;

public interface CustomerService {

    void addCustomer(String name, String additionalInformation);

    void deleteCustomer(int id) throws WrongIdException;

    void updateCustomer(String name, String additionalInformation, int id) throws WrongIdException;

    Collection<Customer> getCustomerList();

}
