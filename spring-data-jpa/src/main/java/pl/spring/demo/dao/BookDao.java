package pl.spring.demo.dao;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookSearchCriteriaTo;

import java.util.List;

public interface BookDao extends Dao<BookEntity, Long> {

    List<BookEntity> findBooks(BookSearchCriteriaTo bookSearchCriteria);
}
