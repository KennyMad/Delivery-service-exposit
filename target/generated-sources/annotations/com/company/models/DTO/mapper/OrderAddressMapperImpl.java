package com.company.models.DTO.mapper;

import com.company.models.DTO.OrderAddressDTO;
import com.company.models.OrderAddress;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-20T00:48:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
public class OrderAddressMapperImpl implements OrderAddressMapper {

    @Override
    public OrderAddressDTO orderAddressToOrderAddressDTO(OrderAddress orderAddress) {
        if ( orderAddress == null ) {
            return null;
        }

        OrderAddressDTO orderAddressDTO = new OrderAddressDTO();

        orderAddressDTO.setHouse( orderAddress.getHouse() );
        orderAddressDTO.setStreet( orderAddress.getStreet() );
        orderAddressDTO.setCity( orderAddress.getCity() );

        return orderAddressDTO;
    }

    @Override
    public OrderAddress orderAddressDTOtoOrderAddress(OrderAddressDTO orderAddressDTO) {
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
