package pl.spring.demo.service;

import pl.spring.demo.to.BookLoanRequestTo;
import pl.spring.demo.to.BookLoanResultTo;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookService {
    List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria);

    BookLoanResultTo loanBook(BookLoanRequestTo bookLoanRequest);
}
