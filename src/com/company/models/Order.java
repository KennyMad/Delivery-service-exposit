package com.company.models;

import java.util.HashMap;
import java.util.List;

public class Order {

    private int id;

    private Customer customer;

    private OrderAddress orderAddress;

    private HashMap<Integer, List<Product>> productsByStoreId;

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public void setProductsByStoreId(HashMap<Integer, List<Product>> productsByStoreId) {
        this.productsByStoreId = productsByStoreId;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public HashMap<Integer, List<Product>> getProductsByStoreId() {
        return productsByStoreId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderAddress=" + orderAddress +
                ", productsByStoreId=" + productsByStoreId +
                '}';
    }
}
