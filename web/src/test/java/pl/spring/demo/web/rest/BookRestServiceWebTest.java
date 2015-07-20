package pl.spring.demo.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        BookTo book = new BookTo();
        book.setId(10L);
        book.setTitle("Clean Code");

        when(bookService.findBooks(any(BookSearchCriteriaTo.class))).thenReturn(Arrays.asList(book));
        // when
        mockMvc.perform(get("/books?title=Clean&author=UncleBob&hasSpoiler=false&available=true"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(10))
                .andExpect(jsonPath("[0].title").value("Clean Code"));

        ArgumentCaptor<BookSearchCriteriaTo> captor = ArgumentCaptor.forClass(BookSearchCriteriaTo.class);
        verify(bookService).findBooks(captor.capture());
        BookSearchCriteriaTo bookSearchCriteria = captor.getValue();
        assertEquals("Clean", bookSearchCriteria.getTitle());
        assertEquals("UncleBob", bookSearchCriteria.getAuthor());
        assertEquals(false, bookSearchCriteria.getHasSpoiler());
        assertEquals(true, bookSearchCriteria.getAvailable());
    }

    @Test
    public void loanBookShouldCallBookServiceAndReturnRequestResult() throws Exception {
        // given
        byte[] requestContent = readFileToBytes("classpath:requests/loanBook.json");
        when(bookService.loanBook(any(BookLoanRequestTo.class))).thenReturn(new BookLoanResultTo(BookLoanStatus.SUCCESS));
        // when
        ResultActions resultActions = mockMvc.perform(post("/book-loan")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON));
        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("status").value("SUCCESS"));

        ArgumentCaptor<BookLoanRequestTo> captor = ArgumentCaptor.forClass(BookLoanRequestTo.class);
        verify(bookService).loanBook(captor.capture());
        BookLoanRequestTo bookLoanRequestTo = captor.getValue();
        assertEquals(1L, bookLoanRequestTo.getBookExemplarId());
        assertEquals(2L, bookLoanRequestTo.getCustomerId());
    }

    private byte[] readFileToBytes(String resourcePath) throws IOException {
        return Files.readAllBytes(wac.getResource(resourcePath).getFile().toPath());
    }
}