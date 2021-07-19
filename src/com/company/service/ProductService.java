package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.DTO.ProductDTO;
import com.company.models.Product;
import com.company.models.ProductCategory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ProductService {

    void add(ProductDTO productDTO) throws WrongIdException;

    void delete(int productId) throws WrongIdException;

    void update(ProductDTO productDTO) throws WrongIdException;

    Collection<ProductDTO> getProductList();

    Collection<ProductDTO> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException;

    Collection<ProductDTO> getProductsByPrice(boolean reversed);

    Collection<ProductDTO> getProductsByCategory(ProductCategory category);

    Collection<ProductDTO> getProductsByStore(int storeId) throws WrongIdException;
}
