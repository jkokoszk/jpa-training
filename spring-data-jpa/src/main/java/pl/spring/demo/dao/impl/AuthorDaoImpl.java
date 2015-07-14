package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Repository;
import pl.spring.demo.dao.AuthorDao;
import pl.spring.demo.entity.AuthorEntity;

@Repository
public class AuthorDaoImpl extends AbstractDao<AuthorEntity, Long> implements AuthorDao {
}
