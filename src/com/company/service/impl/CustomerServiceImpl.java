package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.CustomerDTO;
import com.company.models.DTO.mapper.CustomerMapper;
import com.company.repository.CustomerDAO;
import com.company.service.CustomerService;
import com.company.utils.impl.SequenceGenerator;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDao;

    public CustomerServiceImpl(CustomerDAO customerDao){
        this.customerDao = customerDao;
    }

    @Override
    public void add(CustomerDTO customerDTO){
        Customer customer = CustomerMapper.CUSTOMER_MAPPER.customerDTOtoCustomer(customerDTO);
        customer.setId(SequenceGenerator.getFreeCustomerId(customerDao.readAll()));
        customerDao.add(customer);
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(customerDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(CustomerDTO customerDTO) throws WrongIdException{
        Customer updatedCustomer = CustomerMapper.CUSTOMER_MAPPER.customerDTOtoCustomer(customerDTO);
        Customer customer = customerDao.getById(updatedCustomer.getId());

        if (customer == null)
            throw new WrongIdException(updatedCustomer.getId());

        customer.setName(updatedCustomer.getName());
        customer.setAdditionalInformation(updatedCustomer.getAdditionalInformation());
    }

    @Override
    public Collection<CustomerDTO> getCustomerList() {
        return customerDao.readAll().stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
}
