package com.company.facade.impl;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.*;
import com.company.service.CustomerService;
import com.company.service.OrderService;
import com.company.service.ProductService;
import com.company.service.StoreService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeImpl implements Facade {

    final CustomerService customerService;
    final OrderService orderService;
    final StoreService storeService;
    final ProductService productService;

    public FacadeImpl(CustomerService customerService, OrderService orderService, StoreService storeService, ProductService productService){
        this.customerService = customerService;
        this.orderService = orderService;
        this.storeService = storeService;
        this.productService = productService;
    }

    @Override
    public void registerCustomer(String name, String additionalInformation) throws SaveDataException {
        customerService.add(name, additionalInformation);
    }

    @Override
    public void deleteCustomer(int id) throws WrongIdException, SaveDataException {
        customerService.delete(id);
    }

    @Override
    public void updateCustomer(String name, String additionalInformation, int id) throws WrongIdException, SaveDataException {
        customerService.update(name, additionalInformation, id);
    }

    @Override
    public Collection<Customer> getCustomerList() {
        return customerService.getCustomerList();
    }

    @Override
    public void registerStore(String name, String description) throws SaveDataException {
        storeService.add(name, description);
    }

    @Override
    public void deleteStore(int id) throws WrongIdException, SaveDataException {
        storeService.delete(id);
    }

    @Override
    public void updateStore(String name, String description, int id) throws WrongIdException, SaveDataException {
        storeService.update(name, description, id);
    }

    @Override
    public Collection<Store> getStoreList() {
        return storeService.getStoreList();
    }

    @Override
    public void addProduct(int storeId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException, SaveDataException {
        productService.add(storeId, name, description, amount, price, categories);
    }

    @Override
    public void deleteProduct(int storeId, int productId) throws WrongIdException, SaveDataException {
        productService.delete(storeId, productId);
    }

    @Override
    public void updateProduct(int storeId, int productId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException, SaveDataException {
        productService.update(storeId, productId, name, description, amount, price, categories);
    }

    @Override
    public Collection<Product> getProductList() {
        return productService.getProductList();
    }

    @Override
    public Collection<Product> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException {
        return productService.getProductsByAttributes(nameValueMap);
    }

    @Override
    public Collection<Product> getProductsByPrice(boolean reversed) {
        return productService.getProductsByPrice(reversed);
    }

    @Override
    public Collection<Product> getProductsByCategory(ProductCategory category) {
        return productService.getProductsByCategory(category);
    }

    @Override
    public Collection<Product> getProductsByStore(int storeId) throws WrongIdException {
        return productService.getProductsByStore(storeId);
    }

    @Override
    public void createOrder(int customerId, HashMap<Integer, List<Product>> productsByStoreId, OrderAddress orderAddress) throws WrongIdException, SaveDataException {
        orderService.add(customerId, productsByStoreId, orderAddress);
    }

    @Override
    public Collection<Order> getOrderList() {
        return orderService.getOrderList();
    }
}
