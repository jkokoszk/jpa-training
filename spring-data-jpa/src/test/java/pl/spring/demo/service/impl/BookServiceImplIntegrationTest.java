package pl.spring.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceImplIntegrationTest {

    @Autowired
    private BookService bookService;

    @Test
    public void findBooksShouldWork() {
        // given
        // TODO Implement test
        // when
        List<BookTo> books = bookService.findBooks(new BookSearchCriteriaTo());
        // then
    }
}
