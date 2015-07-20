package pl.spring.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import pl.spring.demo.dao.CustomerCardDao;
import pl.spring.demo.dao.CustomerDao;
import pl.spring.demo.entity.CustomerCardEntity;
import pl.spring.demo.entity.CustomerEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class OrphanRemovalTest extends AbstractDatabaseTest {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerCardDao customerCardDao;

    @Test
    public void customersCardsShouldBeRemovedAfterUnbindingFromCustomer() {

        final long customerId = 1L;

        transactionTemplate.execute((TransactionStatus transactionStatus) -> {
            CustomerEntity customerEntity = customerDao.find(customerId);
            customerEntity.getCards().stream().forEach(c -> c.setCustomerEntity(null));
            customerEntity.getCards().clear();
            customerDao.update(customerEntity);
            return null;
        });

        transactionTemplate.execute((TransactionStatus transactionStatus) -> {
            List<CustomerCardEntity> cards = customerCardDao.findAll();
            assertEquals(0, cards.size());
            return null;
        });
    }

}
