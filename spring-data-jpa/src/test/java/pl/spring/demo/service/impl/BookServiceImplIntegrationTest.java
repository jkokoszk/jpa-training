package pl.spring.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.*;
import pl.spring.demo.type.AudioBookFormat;
import pl.spring.demo.type.BookCover;
import pl.spring.demo.type.PaperSize;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceImplIntegrationTest extends AbstractDatabaseTest {

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

    @Test
    public void loanBookShouldWork() {
        // TODO Implement
    }

    @Test
    public void testShouldCreateNewBook() {
        // given
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        // when
        BookTo alreadySavedBook = bookService.createBook(bookToSave);
        // then
        assertNotNull(alreadySavedBook.getId());
        BookTo bookTo = bookService.findBookById(alreadySavedBook.getId());
        assertNotNull(bookTo);
        assertTrue(bookTo.getAuthors().isEmpty());
    }

    @Test
    public void testShouldCreateNewBookWithAuthor() {
        // given
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setAuthors(new HashSet<>(Arrays.asList(1L)));
        // when
        BookTo alreadySavedBook = bookService.createBook(bookToSave);
        // then
        assertNotNull(alreadySavedBook.getId());
        BookTo bookTo = bookService.findBookById(alreadySavedBook.getId());
        assertNotNull(bookTo);
        assertEquals(1, bookTo.getAuthors().size());
    }

    @Test(expected = javax.persistence.EntityNotFoundException.class)
    public void testShouldCreateNewBookWithAuthor2() {
        // given
        final long idOfNonExistentAuthor = 222L;
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setAuthors(new HashSet<>(Arrays.asList(idOfNonExistentAuthor)));
        // when
        bookService.createBook(bookToSave);
        // then
        fail("Should end with EntityNotFoundException");
    }

    @Test
    public void testShouldCreateNewBookWithSpoiler() {
        // given
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setSpoiler("Spoiler sample");
        // when
        BookTo alreadySavedBook = bookService.createBook(bookToSave);
        // then
        assertNotNull(alreadySavedBook.getId());
        assertEquals(bookToSave.getSpoiler(), bookService.findBookSpoiler(alreadySavedBook.getId()));
    }

    @Test
    public void testShouldCreateNewBookWithExemplars() {
        // given
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");

        PaperBookExemplarTo paperBookExemplar1 = new PaperBookExemplarTo("123", 333, PaperSize.A_4, BookCover.HARD);
        PaperBookExemplarTo paperBookExemplar2 = new PaperBookExemplarTo("234", 222, PaperSize.B_5, BookCover.SOFT);
        AudioBookExemplarTo audioBookExemplar = new AudioBookExemplarTo("321", AudioBookFormat.AUDIO);
        bookToSave.setExemplars(new HashSet<>(Arrays.asList(paperBookExemplar1, paperBookExemplar2, audioBookExemplar)));
        // when
        BookTo alreadySavedBook = bookService.createBook(bookToSave);
        // then
        assertNotNull(alreadySavedBook.getId());
        assertEquals(3, bookService.findBookExemplars(alreadySavedBook.getId()).size());
    }

}
