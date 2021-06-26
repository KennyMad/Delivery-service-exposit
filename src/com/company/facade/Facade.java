package com.company.facade;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Facade {

    void registerCustomer(String name, String additionalInformation);

    void deleteCustomer(int id) throws WrongIdException;

    void updateCustomer(String name, String additionalInformation, int id) throws WrongIdException;

    Collection<Customer> getCustomerList();

    void registerStore(String name, String description);

    void deleteStore(int id) throws WrongIdException;

    void updateStore(String name, String description, int id) throws WrongIdException;

    Collection<Store> getStoreList();

    void addProduct(int storeId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException;

    void deleteProduct(int storeId, int productId) throws WrongIdException;

    void updateProduct(int storeId, int productId , String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException;

    Collection<Product> getProductList();

    Collection<Product> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException;

    Collection<Product> getProductsByPrice(boolean reversed);

    Collection<Product> getProductsByCategory(ProductCategory category);

    Collection<Product> getProductsByStore(int storeId) throws WrongIdException;

    void createOrder(int customerId, HashMap<Integer, List<Product>> productsByStoreId, OrderAddress orderAddress) throws WrongIdException;

    Collection<Order> getOrderList();


}
