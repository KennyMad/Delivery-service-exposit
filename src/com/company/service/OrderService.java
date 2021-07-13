package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Order;
import com.company.models.OrderAddress;
import com.company.models.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface OrderService {

    void createOrder(int customerId, HashMap<Integer, List<Product>> productsByStoreId, OrderAddress orderAddress) throws WrongIdException, SaveDataException;

    Collection<Order> getOrderList();

}
