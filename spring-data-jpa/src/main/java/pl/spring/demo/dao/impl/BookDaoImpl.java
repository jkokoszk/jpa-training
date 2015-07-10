package pl.spring.demo.dao.impl;

import org.springframework.stereotype.Repository;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookSearchCriteriaTo;

import java.util.List;

@Repository
public class BookDaoImpl extends AbstractDao<BookEntity, Long> implements BookDao {

    @Override
    public List<BookEntity> findBooks(BookSearchCriteriaTo bookSearchCriteria) {
        // TODO Implement
        return findAll();
    }
}
