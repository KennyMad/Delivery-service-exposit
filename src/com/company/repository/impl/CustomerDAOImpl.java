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
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {

    private Map<Integer, Customer> customerMap;

    private final FileService fileService;

    public CustomerDAOImpl(FileService fileService){
        this.fileService = fileService;
    }

    @Override
    public void initialize(){
        try {
            customerMap = (HashMap) fileService.load(Constants.CUSTOMERS_FILE, new TypeToken<HashMap<Integer, Customer>>() {
            }.getType());
        }
        catch (FileNotFoundException exception){
            customerMap = new HashMap<>();
        }
        catch (LoadDataException loadException){
            customerMap = new HashMap<>();
            loadException.printStackTrace();
        }
    }

    @Override
    public void save() throws SaveDataException {
        fileService.save(Constants.CUSTOMERS_FILE, customerMap);
    }

    @Override
    public Collection readAll() {
        return customerMap.values();
    }

    @Override
    public Customer read(int id) throws WrongIdException{
        Customer customer = customerMap.get(id);
        if (customer == null)
            throw new WrongIdException(id);
        return customer;
    }

    @Override
    public void remove(int id) throws WrongIdException {
        if (customerMap.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void add(Customer customer) {
        customerMap.put(customer.getId(),customer);
    }

    @Override
    public int getFreeId(){
        int id = customerMap.size();
        while (customerMap.containsKey(id))
            id++;
        return id;
    }
}
