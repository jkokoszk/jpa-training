package pl.spring.demo.dao;

import pl.spring.demo.entity.Book;
import pl.spring.demo.to.BookSearchCriteriaTo;

import java.util.List;

public interface BookDao extends Dao<Book, Long> {

    List<Book> findBooks(BookSearchCriteriaTo bookSearchCriteria);
}
