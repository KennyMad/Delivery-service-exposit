package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.repository.CustomerDAO;
import com.company.service.CustomerService;
import com.company.utils.impl.SequenceGenerator;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDao;

    public CustomerServiceImpl(CustomerDAO customerDao){
        this.customerDao = customerDao;
    }

    @Override
    public void add(String name, String additionalInformation){
        Customer customer = new Customer();
        customer.setId(SequenceGenerator.getFreeCustomerId(customerDao.readAll()));
        customer.setName(name);
        customer.setAdditionalInformation(additionalInformation);
        customerDao.add(customer);
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(customerDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(String name, String additionalInformation, int id) throws WrongIdException{
        Customer customer = customerDao.getById(id);

        if (customer == null)
            throw new WrongIdException(id);

        customer.setName(name);
        customer.setAdditionalInformation(additionalInformation);
    }

    @Override
    public Collection<Customer> getCustomerList() {
        return customerDao.readAll();
    }
}
