package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookLoanRequestTo;
import pl.spring.demo.to.BookLoanResultTo;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

import java.util.List;

@RestController
public class BookRestService {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria) {
        return bookService.findBooks(searchCriteria);
    }

    @RequestMapping(value = "/book-loan", method = RequestMethod.POST)
    public BookLoanResultTo loanBook(@RequestBody BookLoanRequestTo bookLoanRequest) {
        return bookService.loanBook(bookLoanRequest);
    }

}
