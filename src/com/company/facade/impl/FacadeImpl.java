package com.company.facade.impl;

import com.company.constants.Constants;
import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.*;
import com.company.models.DTO.*;
import com.company.models.DTO.mapper.CustomerMapper;
import com.company.models.DTO.mapper.OrderMapper;
import com.company.models.DTO.mapper.ProductMapper;
import com.company.models.DTO.mapper.StoreMapper;
import com.company.service.CustomerService;
import com.company.service.OrderService;
import com.company.service.ProductService;
import com.company.service.StoreService;
import com.company.utils.FileUtil;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class FacadeImpl implements Facade {

    final CustomerService customerService;
    final OrderService orderService;
    final StoreService storeService;
    final ProductService productService;

    final FileUtil fileUtil;

    public FacadeImpl(CustomerService customerService, OrderService orderService, StoreService storeService, ProductService productService, FileUtil fileUtil){
        this.customerService = customerService;
        this.orderService = orderService;
        this.storeService = storeService;
        this.productService = productService;

        this.fileUtil = fileUtil;
    }

    @Override
    public void registerCustomer(CustomerDTO customerDTO) throws SaveDataException {
        customerService.add(customerDTO);
    }

    @Override
    public void deleteCustomer(int id) throws WrongIdException, SaveDataException {
        customerService.delete(id);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) throws WrongIdException, SaveDataException {
        customerService.update(customerDTO);
    }

    @Override
    public Collection<CustomerDTO> getCustomerList() {
        return customerService.getCustomerList();
    }

    @Override
    public void registerStore(StoreDTO storeDTO) throws SaveDataException {
        storeService.add(storeDTO);
    }

    @Override
    public void deleteStore(int id) throws WrongIdException, SaveDataException {
        storeService.delete(id);
    }

    @Override
    public void updateStore(StoreDTO storeDTO) throws WrongIdException, SaveDataException {
        storeService.update(storeDTO);
    }

    @Override
    public Collection<StoreDTO> getStoreList() {
        return storeService.getStoreList();
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws WrongIdException, SaveDataException {
        productService.add(productDTO);
    }

    @Override
    public void deleteProduct(int productId) throws WrongIdException, SaveDataException {
        productService.delete(productId);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) throws WrongIdException, SaveDataException {
        productService.update(productDTO);
    }

    @Override
    public Collection<ProductDTO> getProductList() {
        return productService.getProductList();
    }

    @Override
    public Collection<ProductDTO> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException {
        return productService.getProductsByAttributes(nameValueMap);
    }

    @Override
    public Collection<ProductDTO> getProductsByPrice(boolean reversed) {
        return productService.getProductsByPrice(reversed);
    }

    @Override
    public Collection<ProductDTO> getProductsByCategory(ProductCategory category) {
        return productService.getProductsByCategory(category);
    }

    @Override
    public Collection<ProductDTO> getProductsByStore(int storeId) throws WrongIdException {
        return productService.getProductsByStore(storeId);
    }

    @Override
    public void createOrder(OrderDTO orderDTO) throws WrongIdException, SaveDataException {
        orderService.add(orderDTO);
    }

    @Override
    public Collection<OrderDTO> getOrderList() {
        return orderService.getOrderList();
    }

    @Override
    public void saveData() throws SaveDataException {
        fileUtil.save(customerService.getCustomerList().stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::customerDTOtoCustomer)
                .collect(Collectors.toList()), Constants.CUSTOMERS_FILE);

        fileUtil.save(orderService.getOrderList().stream()
                .map(OrderMapper.ORDER_MAPPER::orderDTOtoOrder)
                .collect(Collectors.toList()), Constants.ORDERS_FILE);

        fileUtil.save(storeService.getStoreList().stream()
                .map(StoreMapper.STORE_MAPPER::storeDTOtoStore)
                .collect(Collectors.toList()), Constants.STORE_FILE);

        fileUtil.save(productService.getProductList().stream()
                .map(ProductMapper.PRODUCT_MAPPER::productDTOtoProduct)
                .collect(Collectors.toList()), Constants.PRODUCT_FILE);
    }
}
