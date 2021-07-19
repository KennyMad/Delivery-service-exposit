package com.company.models.DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDTO {

    private int id;

    private int customerId;

    private OrderAddressDTO orderAddressDTO;

    private HashMap<Integer, Integer> productIdCountMap;

    public OrderDTO(){}

    public OrderDTO(int customerId, OrderAddressDTO orderAddressDTO, HashMap<Integer, Integer> productIdCountMap) {
        this.customerId = customerId;
        this.orderAddressDTO = orderAddressDTO;
        this.productIdCountMap = productIdCountMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public OrderAddressDTO getOrderAddressDTO() {
        return orderAddressDTO;
    }

    public void setOrderAddressDTO(OrderAddressDTO orderAddressDTO) {
        this.orderAddressDTO = orderAddressDTO;
    }

    public HashMap<Integer, Integer> getProductIdCountMap() {
        return productIdCountMap;
    }

    public void setProductIdCountMap(HashMap<Integer, Integer> productIdCountMap) {
        this.productIdCountMap = productIdCountMap;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderAddressDTO=" + orderAddressDTO +
                ", productIdCountMap=" + productIdCountMap +
                '}';
    }
}
