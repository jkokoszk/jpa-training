package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Repository;
import pl.spring.demo.dao.LoanDao;
import pl.spring.demo.entity.LoanEntity;

@Repository
public class LoanDaoImpl extends AbstractDao<LoanEntity, Long> implements LoanDao {
}
