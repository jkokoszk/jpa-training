package pl.spring.demo.cache;

import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookCache {

    List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria);
}
