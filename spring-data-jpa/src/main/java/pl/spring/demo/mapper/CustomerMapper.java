package pl.spring.demo.mapper;

import org.springframework.stereotype.Component;
import pl.spring.demo.entity.CustomerEntity;
import pl.spring.demo.to.CustomerTo;

@Component
public class CustomerMapper extends AbstractMapper<CustomerEntity, CustomerTo> {

    @Override
    public CustomerTo mapSource(CustomerEntity source) {
        CustomerTo customerTo = new CustomerTo();
        customerTo.setId(source.getId());
        customerTo.setEmail(source.getEmail());
        customerTo.setPersonalData(source.getPersonalData());
        customerTo.setPhone(source.getPhoneNumber());
        return customerTo;
    }

    @Override
    public CustomerEntity mapTarget(CustomerTo target) {
        return new CustomerEntity(target.getId(), target.getPersonalData(), target.getPhone(), target.getEmail());
    }

}
