package pl.spring.demo.service;

import pl.spring.demo.to.CustomerTo;

public interface CustomerService {

    CustomerTo createCustomer(CustomerTo customer);
}
