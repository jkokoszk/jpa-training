package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.spring.demo.cache.BookCache;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.*;

import java.util.List;

@RestController
public class BookRestService {

    private final BookService bookService;
    private final BookCache bookCache;

    @Autowired
    public BookRestService(BookService bookService, BookCache bookCache) {
        this.bookService = bookService;
        this.bookCache = bookCache;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria) {
        return bookService.findBooks(searchCriteria);
    }

    @RequestMapping(value = "/books-cache", method = RequestMethod.GET)
    @ResponseBody
    public List<BookTo> findBooksInCache(BookSearchCriteriaTo searchCriteria) {
        return bookCache.findBooks(searchCriteria);
    }

    @RequestMapping(value = "/book-loan", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public BookLoanResultTo loanBook(@RequestBody BookLoanRequestTo bookLoanRequest) {
        return bookService.loanBook(bookLoanRequest);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseBody
    public BookTo createBook(@RequestBody NewBookTo bookToSave) {
        return bookService.createBook(bookToSave);
    }

}
