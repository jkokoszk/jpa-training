package pl.spring.demo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.Book;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

public class BookServiceImplMockTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookDao bookDao;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findBooksShouldWork() {
        // given
        List<Book> bookEntities = new ArrayList<>();
        when(bookDao.findBooks(any(BookSearchCriteriaTo.class))).thenReturn(bookEntities);
        List<BookTo> bookTos = new ArrayList<>();
        when(bookMapper.mapSourceCollection(bookEntities)).thenReturn(bookTos);
        // when
        List<BookTo> results = bookService.findBooks(new BookSearchCriteriaTo());
        // then
        assertEquals(bookTos, results);
    }
}
