package pl.spring.demo.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.spring.demo.to.BookLoanRequestTo;
import pl.spring.demo.to.BookLoanResultTo;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

import java.util.List;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findBooksShouldWork() {
        // TODO Implement test with mocks
        // given
        // when
        List<BookTo> books = bookService.findBooks(new BookSearchCriteriaTo());
        // then
    }

    @Test
    public void loanBookShouldWork() {
        // TODO Implement test with mocks
        // given
        // when
        BookLoanResultTo bookLoanResultTo = bookService.loanBook(new BookLoanRequestTo());
        // then
    }
}
