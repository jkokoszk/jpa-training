package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Repository;

import pl.spring.demo.dao.AuthorDao;
import pl.spring.demo.entity.Author;

@Repository
public class AuthorDaoImpl extends AbstractDao<Author, Long> implements AuthorDao {

}
