package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Repository;
import pl.spring.demo.dao.AuthorDao;
import pl.spring.demo.entity.Author;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorDaoImpl extends AbstractDao<Author, Long> implements AuthorDao {

    @Override
    public <T extends Author> List<T> findAllAuthors(Class<T> clazz) {
        TypedQuery<T> query = entityManager.createQuery("from " + clazz.getName(), clazz);
        return query.getResultList();
    }
}
