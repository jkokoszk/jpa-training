package pl.spring.demo.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookLoanRequestTo;
import pl.spring.demo.to.BookLoanResultTo;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;


public class BookRestServiceTest {

    @InjectMocks
    private BookRestService bookRestService;

    @Mock
    private BookService bookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findBooksShouldCallService() {
        // given
        List<BookTo> books = new ArrayList<>();
        BookSearchCriteriaTo searchCriteria = new BookSearchCriteriaTo();
        when(bookService.findBooks(searchCriteria)).thenReturn(books);
        // when
        List<BookTo> result = bookRestService.findBooks(searchCriteria);
        // then
        assertSame(books, result);
    }

    @Test
    public void loanBookShouldCallService() {
        // given
        BookLoanRequestTo bookLoanRequest = new BookLoanRequestTo();
        BookLoanResultTo bookLoanResult = new BookLoanResultTo();
        when(bookService.loanBook(bookLoanRequest)).thenReturn(bookLoanResult);
        // when
        BookLoanResultTo result = bookRestService.loanBook(bookLoanRequest);
        // then
        assertSame(bookLoanResult, result);
    }
}