package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Order;
import com.company.models.OrderAddress;
import com.company.models.Product;
import com.company.repository.CustomerDAO;
import com.company.repository.OrderDAO;
import com.company.service.OrderService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    final CustomerDAO customerDAO;
    final OrderDAO orderDAO;

    public OrderServiceImpl(CustomerDAO customerDAO, OrderDAO orderDAO){
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void createOrder(int customerId, HashMap<Integer, List<Product>> productsByStoreId, OrderAddress orderAddress) throws WrongIdException {
        Order order = new Order();
        order.setId(orderDAO.getFreeId());
        order.setCustomer(customerDAO.read(customerId));
        order.setProductsByStoreId(productsByStoreId);
        order.setOrderAddress(orderAddress);
        orderDAO.add(order);

        orderDAO.save();
    }

    @Override
    public Collection<Order> getOrderList() {
        return orderDAO.readAll();
    }
}
