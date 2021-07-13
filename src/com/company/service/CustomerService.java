package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Customer;

import java.util.Collection;

public interface CustomerService {

    void addCustomer(String name, String additionalInformation) throws SaveDataException;

    void deleteCustomer(int id) throws WrongIdException, SaveDataException;

    void updateCustomer(String name, String additionalInformation, int id) throws WrongIdException, SaveDataException;

    Collection<Customer> getCustomerList();

}
