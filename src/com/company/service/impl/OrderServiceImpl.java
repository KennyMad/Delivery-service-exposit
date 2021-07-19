package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.OrderDTO;
import com.company.models.DTO.mapper.OrderMapper;
import com.company.models.Order;
import com.company.repository.CustomerDAO;
import com.company.repository.OrderDAO;
import com.company.service.OrderService;
import com.company.utils.impl.SequenceGenerator;

import java.util.Collection;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    final CustomerDAO customerDAO;
    final OrderDAO orderDAO;

    public OrderServiceImpl(CustomerDAO customerDAO, OrderDAO orderDAO){
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void add(OrderDTO orderDTO) throws WrongIdException{
        Order order = OrderMapper.ORDER_MAPPER.orderDTOtoOrder(orderDTO);
        order.setId(SequenceGenerator.getFreeOrderId(orderDAO.readAll()));

        Customer customer = customerDAO.getById(order.getCustomerId());

        if (customer == null)
            throw new WrongIdException(order.getCustomerId());
        orderDAO.add(order);
    }

    @Override
    public Collection<OrderDTO> getOrderList() {
        return orderDAO.readAll().stream()
                .map(OrderMapper.ORDER_MAPPER::orderToOrderDTO)
                .collect(Collectors.toList());
    }
}
