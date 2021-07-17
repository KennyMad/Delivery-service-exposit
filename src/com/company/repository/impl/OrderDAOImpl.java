package com.company.repository.impl;

import com.company.constants.Constants;
import com.company.exception.LoadDataException;
import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Order;
import com.company.repository.OrderDAO;
import com.company.service.FileService;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    Map<Integer,Order> orderMap;

    private final FileService fileService;

    public OrderDAOImpl(FileService fileService){
        this.fileService = fileService;
    }

    @Override
    public void initialize(){
        try {
            orderMap = (HashMap) fileService.load(Constants.ORDERS_FILE, new TypeToken<HashMap<Integer, Order>>() {
            }.getType());
        }
        catch (FileNotFoundException exception){
            orderMap = new HashMap<>();
        }
        catch (LoadDataException loadException){
            orderMap = new HashMap<>();
            loadException.printStackTrace();
        }
    }

    @Override
    public void save() throws SaveDataException {
        fileService.save(Constants.ORDERS_FILE, orderMap);
    }

    @Override
    public Collection<Order> readAll() {
        return orderMap.values();
    }

    @Override
    public Order getById(int id){
        return orderMap.get(id);

    }

    @Override
    public Order remove(int id) {
        return orderMap.remove(id);
    }

    @Override
    public void add(Order order) {
        orderMap.put(order.getId(),order);
    }

    @Override
    public int getFreeId() {
        int id = orderMap.size();
        while (orderMap.containsKey(id))
            id++;
        return id;
    }
}
