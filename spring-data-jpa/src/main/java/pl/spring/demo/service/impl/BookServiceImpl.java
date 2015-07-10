package pl.spring.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Override
    public List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria) {
        // TODO Implement
        List<BookTo> books = new ArrayList<>();
        books.add(new BookTo("Example title 1"));
        books.add(new BookTo("Example title 2"));
        return books;
    }

    @Override
    public BookLoanResultTo loanBook(BookLoanRequestTo bookLoanRequest) {
        // TODO Implement
        BookLoanResultTo result = new BookLoanResultTo();
        result.setStatus(BookLoanStatus.SUCCESS);
        return result;
    }
}
