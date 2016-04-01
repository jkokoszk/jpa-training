package pl.spring.demo.service;

import pl.spring.demo.to.*;

import java.util.List;

public interface BookService {

    BookTo findBookById(long bookId);

    List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria);

    BookTo createBook(NewBookTo bookToSave);

    BookTo updateBook(BookTo bookTo);

    String findBookReview(long bookId);

    void removeBookById(long bookIdToRemove);
}
