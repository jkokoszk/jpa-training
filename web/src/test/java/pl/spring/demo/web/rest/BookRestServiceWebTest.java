package pl.spring.demo.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class BookRestServiceWebTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private BookService bookService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        reset(bookService);
    }

    @Test
    public void findBooksShouldCallRestService() throws Exception {
        // given
        List<BookTo> books = new ArrayList<>();
        BookTo book = new BookTo();
        book.setId(10L);
        book.setTitle("Clean Code");
        books.add(book);

        when(bookService.findBooks(any(BookSearchCriteriaTo.class))).thenReturn(books);
        // when
        mockMvc.perform(get("/books?title=Clean&author=UncleBob&hasEBook=false&available=true"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(10))
                .andExpect(jsonPath("[0].title").value("Clean Code"));

        ArgumentCaptor<BookSearchCriteriaTo> captor = ArgumentCaptor.forClass(BookSearchCriteriaTo.class);
        verify(bookService).findBooks(captor.capture());
        BookSearchCriteriaTo bookSearchCriteria = captor.getValue();
        assertEquals("Clean", bookSearchCriteria.getTitle());
        assertEquals("UncleBob", bookSearchCriteria.getAuthor());
        assertEquals(false, bookSearchCriteria.getHasEBook());
        assertEquals(true, bookSearchCriteria.getAvailable());
    }

    @Test
    public void testLoanBook() {
        // TODO Implement test
    }
}