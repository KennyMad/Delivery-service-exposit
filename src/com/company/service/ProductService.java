package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.Product;
import com.company.models.ProductCategory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ProductService {

    void add(int storeId, String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException, SaveDataException;

    void delete(int storeId, int productId) throws WrongIdException, SaveDataException;

    void update(int storeId, int productId , String name, String description, int amount, double price, List<ProductCategory> categories) throws WrongIdException, SaveDataException;

    Collection<Product> getProductList();

    Collection<Product> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException;

    Collection<Product> getProductsByPrice(boolean reversed);

    Collection<Product> getProductsByCategory(ProductCategory category);

    Collection<Product> getProductsByStore(int storeId) throws WrongIdException;
}
