package com.company.repository.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.repository.CustomerDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {

    private Map<Integer, Customer> customerMap;

    @Override
    public void initialize(){
        Gson gson = new Gson();

        String jsonCustomers = "";
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("customer.json"))){
            while ((s = reader.readLine()) != null)
                jsonCustomers += s;

            Type collectionType = new TypeToken<HashMap<Integer,Customer>>(){}.getType();
            customerMap = gson.fromJson(jsonCustomers,collectionType);
        }
        catch (FileNotFoundException exception){
            customerMap = new HashMap<>();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void save(){
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("customer.json")){
            writer.write(gson.toJson(customerMap));
        }
        catch (IOException exception){
            System.out.println(exception.fillInStackTrace());
        }
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
