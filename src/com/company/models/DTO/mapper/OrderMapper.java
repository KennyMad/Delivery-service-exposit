package com.company.models.DTO.mapper;

import com.company.models.DTO.OrderDTO;
import com.company.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderAddress", target = "orderAddressDTO")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "orderAddressDTO", target = "orderAddress")
    Order orderDTOtoOrder(OrderDTO orderDTO);
}
