package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Repository;
import pl.spring.demo.dao.CustomerDao;
import pl.spring.demo.entity.CustomerEntity;

@Repository
public class CustomerDaoImpl extends AbstractDao<CustomerEntity, Long> implements CustomerDao {
}
