package com.company.service.impl;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.repository.CustomerDAO;
import com.company.service.CustomerService;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDao;

    public CustomerServiceImpl(CustomerDAO customerDao){
        this.customerDao = customerDao;
    }

    @Override
    public void add(String name, String additionalInformation) throws SaveDataException {
        Customer customer = new Customer();
        customer.setId(customerDao.getFreeId());
        customer.setName(name);
        customer.setAdditionalInformation(additionalInformation);
        customerDao.add(customer);

        customerDao.save();
    }

    @Override
    public void delete(int id) throws WrongIdException, SaveDataException {
        if(customerDao.remove(id) == null)
            throw new WrongIdException(id);

        customerDao.save();
    }

    @Override
    public void update(String name, String additionalInformation, int id) throws WrongIdException, SaveDataException {
        Customer customer = customerDao.getById(id);

        if (customer == null)
            throw new WrongIdException(id);

        customer.setName(name);
        customer.setAdditionalInformation(additionalInformation);

        customerDao.save();
    }

    @Override
    public Collection<Customer> getCustomerList() {
        return customerDao.readAll();
    }
}
