package com.company.repository.impl;

import com.company.constants.Constants;
import com.company.exception.LoadDataException;
import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.repository.CustomerDAO;
import com.company.service.FileService;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {

    private List<Customer> customerList;

    private final FileService fileService;

    public CustomerDAOImpl(FileService fileService){
        this.fileService = fileService;
    }

    @Override
    public void initialize(){
    }

    @Override
    public void save() throws SaveDataException {
    }

    @Override
    public Collection readAll() {
        return customerList;
    }

    @Override
    public Customer getById(int id){
        return customerList.stream().filter(c -> c.getId() == id).limit(1).findFirst().get();
    }

    @Override
    public Customer remove(int id) {
        Customer customer = getById(id);
        if (customer != null)
            customerList.remove(customer);
        return customer;
    }

    @Override
    public void add(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public int getFreeId(){
        return 0;
    }
}
