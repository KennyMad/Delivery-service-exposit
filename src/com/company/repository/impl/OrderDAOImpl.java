package com.company.repository.impl;

import com.company.exception.WrongIdException;
import com.company.models.Order;
import com.company.repository.OrderDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    Map<Integer,Order> orderMap;

    @Override
    public void initialize() {
        Gson gson = new Gson();

        String jsonCustomers = "";
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.json"))){
            while ((s = reader.readLine()) != null)
                jsonCustomers += s;

            Type collectionType = new TypeToken<HashMap<Integer, Order>>(){}.getType();
            orderMap = gson.fromJson(jsonCustomers,collectionType);
        }
        catch (FileNotFoundException exception){
            orderMap = new HashMap<>();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save() {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("orders.json")){
            writer.write(gson.toJson(orderMap));
        }
        catch (IOException exception){
            System.out.println(exception.fillInStackTrace());
        }
    }

    @Override
    public Collection<Order> readAll() {
        return orderMap.values();
    }

    @Override
    public Order read(int id) throws WrongIdException {
        Order order = orderMap.get(id);

        if (order == null)
            throw new WrongIdException(id);

        return order;
    }

    @Override
    public void remove(int id) throws WrongIdException{
        if(orderMap.remove(id) == null)
            throw new WrongIdException(id);
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
