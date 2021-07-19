package com.company.facade;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.*;
import com.company.models.DTO.CustomerDTO;
import com.company.models.DTO.OrderDTO;
import com.company.models.DTO.ProductDTO;
import com.company.models.DTO.StoreDTO;

import java.util.Collection;
import java.util.Map;

public interface Facade {

    void registerCustomer(CustomerDTO customerDTO) throws SaveDataException;

    void deleteCustomer(int id) throws WrongIdException, SaveDataException;

    void updateCustomer(CustomerDTO customerDTO) throws WrongIdException, SaveDataException;

    Collection<CustomerDTO> getCustomerList();

    void registerStore(StoreDTO storeDTO) throws SaveDataException;

    void deleteStore(int id) throws WrongIdException, SaveDataException;

    void updateStore(StoreDTO storeDTO) throws WrongIdException, SaveDataException;

    Collection<StoreDTO> getStoreList();

    void addProduct(ProductDTO productDTO) throws WrongIdException, SaveDataException;

    void deleteProduct(int productId) throws WrongIdException, SaveDataException;

    void updateProduct(ProductDTO productDTO) throws WrongIdException, SaveDataException;

    Collection<ProductDTO> getProductList();

    Collection<ProductDTO> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException;

    Collection<ProductDTO> getProductsByPrice(boolean reversed);

    Collection<ProductDTO> getProductsByCategory(ProductCategory category);

    Collection<ProductDTO> getProductsByStore(int storeId) throws WrongIdException;

    void createOrder(OrderDTO orderDTO) throws WrongIdException, SaveDataException;

    Collection<OrderDTO> getOrderList();

    void saveData() throws SaveDataException;

}
