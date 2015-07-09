package pl.spring.demo.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookRestService {

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookTo> getAllBooks() {
        List<BookTo> books = new ArrayList<>();
        books.add(new BookTo("Example title 1"));
        books.add(new BookTo("Example title 2"));
        return books;
    }
}
