package com.company.models.DTO.mapper;

import com.company.models.DTO.OrderAddressDTO;
import com.company.models.OrderAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderAddressMapper {

    OrderAddressMapper ORDER_ADDRESS_MAPPER = Mappers.getMapper(OrderAddressMapper.class);

    OrderAddressDTO orderAddressToOrderAddressDTO(OrderAddress orderAddress);

    OrderAddress orderAddressDTOtoOrderAddress(OrderAddressDTO orderAddressDTO);

}
