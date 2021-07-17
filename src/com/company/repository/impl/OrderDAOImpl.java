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
import java.util.List;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    List<Order> orderList;

    private final FileService fileService;

    public OrderDAOImpl(FileService fileService){
        this.fileService = fileService;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void save() throws SaveDataException {
    }

    @Override
    public Collection<Order> readAll() {
        return orderList;
    }

    @Override
    public Order getById(int id){
        return orderList.stream().filter(o -> o.getId() == id).limit(1).findFirst().get();
    }

    @Override
    public Order remove(int id) {
        Order order = getById(id);
        if (order != null)
            orderList.remove(order);
        return order;
    }

    @Override
    public void add(Order order) {
        orderList.add(order);
    }

    @Override
    public int getFreeId() {
        return 0;
    }
}
