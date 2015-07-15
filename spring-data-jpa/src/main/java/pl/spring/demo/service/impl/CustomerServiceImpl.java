package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.demo.dao.CustomerDao;
import pl.spring.demo.entity.CustomerEntity;
import pl.spring.demo.mapper.CustomerMapper;
import pl.spring.demo.service.CustomerService;
import pl.spring.demo.to.CustomerTo;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerDao customerDao) {
        this.customerMapper = customerMapper;
        this.customerDao = customerDao;
    }

    @Override
    public CustomerTo createCustomer(CustomerTo customerTo) {
        CustomerEntity customerEntity = customerMapper.mapTarget(customerTo);
        customerDao.save(customerEntity);
        return customerMapper.mapSource(customerEntity);
    }
}
