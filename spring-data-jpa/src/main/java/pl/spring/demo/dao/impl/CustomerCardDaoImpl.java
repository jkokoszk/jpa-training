package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Repository;
import pl.spring.demo.dao.CustomerCardDao;
import pl.spring.demo.entity.CustomerCardEntity;

@Repository
public class CustomerCardDaoImpl extends AbstractDao<CustomerCardEntity, Long> implements CustomerCardDao {

}
