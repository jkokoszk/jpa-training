package pl.spring.demo.service.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class OrphanRemovalTest extends AbstractDatabaseTest {

    @Autowired
    private TransactionTemplate transactionTemplate;


//    @Test
//    public void customersCardsShouldBeRemovedAfterUnbindingFromCustomer() {
//
//        final long customerId = 1L;
//
//        transactionTemplate.execute((TransactionStatus transactionStatus) -> {
//            CustomerEntity customerEntity = customerDao.find(customerId);
//            customerEntity.getCards().stream().forEach(c -> c.setCustomerEntity(null));
//            customerEntity.getCards().clear();
//            customerDao.update(customerEntity);
//            return null;
//        });
//
//        assertEquals(1, customerCardDao.count()); // the only card without assigned customer
//    }

}
