package com.company.service;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.Product;
import com.company.models.ProductCategory;
import com.company.models.Store;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StoreService {

    void addStore(String name,String description);

    void deleteStore(int id) throws WrongIdException;

    void updateStore(String name, String description, int id) throws WrongIdException;

    Collection<Store> getStoreList();

    void addProduct(int storeId, String name, String description,  int amount, double price,List<ProductCategory> categories) throws WrongIdException;

    void deleteProduct(int storeId, int productId) throws WrongIdException;

    void updateProduct(int storeId, int productId , String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException;

    Collection<Product> getProductList();

    Collection<Product> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException;

    Collection<Product> getProductsByPrice(boolean reversed);

    Collection<Product> getProductsByCategory(ProductCategory category);

    Collection<Product> getProductsByStore(int storeId) throws WrongIdException;

}
