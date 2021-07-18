package com.company.utils.impl;

import com.company.models.*;

import java.util.Collection;
import java.util.stream.Collectors;

public class SequenceGenerator {

    public static int getFreeCustomerId(Collection<Customer> customers){
        return getFreeId(customers.stream().map(Customer::getId).collect(Collectors.toList()));
    }

    public static int getFreeOrderId(Collection<Order> orders){
        return getFreeId(orders.stream().map(Order::getId).collect(Collectors.toList()));
    }

    public static int getFreeProductId(Collection<Product> products){
        return getFreeId(products.stream().map(Product::getId).collect(Collectors.toList()));
    }

    public static int getFreeStoreId(Collection<Store> stores) {
        return getFreeId(stores.stream().map(Store::getId).collect(Collectors.toList()));
    }

    private static int getFreeId(Collection<Integer> ids) {
        int id = ids.size();
        while (ids.contains(id))
            id++;
        return id;
    }

}
