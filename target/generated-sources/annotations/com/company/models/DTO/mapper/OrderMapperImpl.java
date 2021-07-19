package com.company.models.DTO.mapper;

import com.company.models.DTO.OrderAddressDTO;
import com.company.models.DTO.OrderDTO;
import com.company.models.Order;
import com.company.models.OrderAddress;
import java.util.HashMap;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-20T00:48:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderAddressDTO( orderAddressToOrderAddressDTO( order.getOrderAddress() ) );
        orderDTO.setId( order.getId() );
        orderDTO.setCustomerId( order.getCustomerId() );
        HashMap<Integer, Integer> hashMap = order.getProductIdCountMap();
        if ( hashMap != null ) {
            orderDTO.setProductIdCountMap( new HashMap<Integer, Integer>( hashMap ) );
        }

        return orderDTO;
    }

    @Override
    public Order orderDTOtoOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderAddress( orderAddressDTOToOrderAddress( orderDTO.getOrderAddressDTO() ) );
        order.setId( orderDTO.getId() );
        order.setCustomerId( orderDTO.getCustomerId() );
        HashMap<Integer, Integer> hashMap = orderDTO.getProductIdCountMap();
        if ( hashMap != null ) {
            order.setProductIdCountMap( new HashMap<Integer, Integer>( hashMap ) );
        }

        return order;
    }

    protected OrderAddressDTO orderAddressToOrderAddressDTO(OrderAddress orderAddress) {
        if ( orderAddress == null ) {
            return null;
        }

        OrderAddressDTO orderAddressDTO = new OrderAddressDTO();

        orderAddressDTO.setHouse( orderAddress.getHouse() );
        orderAddressDTO.setStreet( orderAddress.getStreet() );
        orderAddressDTO.setCity( orderAddress.getCity() );

        return orderAddressDTO;
    }

    protected OrderAddress orderAddressDTOToOrderAddress(OrderAddressDTO orderAddressDTO) {
        if ( orderAddressDTO == null ) {
            return null;
        }

        OrderAddress orderAddress = new OrderAddress();

        orderAddress.setCity( orderAddressDTO.getCity() );
        orderAddress.setHouse( orderAddressDTO.getHouse() );
        orderAddress.setStreet( orderAddressDTO.getStreet() );

        return orderAddress;
    }
}
