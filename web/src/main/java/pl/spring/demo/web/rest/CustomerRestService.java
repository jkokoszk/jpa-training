package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.spring.demo.service.CustomerService;
import pl.spring.demo.to.CustomerTo;

@RestController
public class CustomerRestService {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public CustomerTo addCustomer(@RequestBody CustomerTo customer) {
        return customerService.createCustomer(customer);
    }
}
