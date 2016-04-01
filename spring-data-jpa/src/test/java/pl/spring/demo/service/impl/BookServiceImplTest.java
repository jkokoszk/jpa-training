package pl.spring.demo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.OptimisticLockException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.NewBookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceImplTest extends AbstractDatabaseTest {

    @Autowired
    private BookService bookService;
    
    @Test
    public void shouldFindBookById(){
    	// given
    	
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        BookTo alreadySavedBook = bookService.createBook(bookToSave);
        // when
        BookTo bookTo = bookService.findBookById(alreadySavedBook.getId());
        // then
        assertNotNull(bookTo);
        assertEquals(bookTo.getId(), alreadySavedBook.getId());
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
    public void testShouldCreateNewBookWithReview() {
        // given
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setReview("Review sample");
        // when
        BookTo alreadySavedBook = bookService.createBook(bookToSave);
        // then
        assertNotNull(alreadySavedBook.getId());
        assertEquals(bookToSave.getReview(), bookService.findBookReview(alreadySavedBook.getId()));
    }


    @Test
    public void updateBookShouldSaveChangesToTheBook() {
        // given
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setReview("Spoiler sample");
        BookTo createdBook = bookService.createBook(bookToSave);

        String updatedTitle = "Updated title";
        createdBook.setTitle(updatedTitle);
        // when
        BookTo savedBook = bookService.updateBook(createdBook);
        // then
        assertEquals(updatedTitle, savedBook.getTitle());
        assertTrue(savedBook.getVersion() > createdBook.getVersion());
    }

    @Test(expected = OptimisticLockException.class)
    public void updateBookShouldThrowOptimisticLockingExceptionWhenUpdatedVersionLowerThanExisting() {
        // given
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setReview("Spoiler sample");
        BookTo createdBook = bookService.createBook(bookToSave);

        createdBook.setVersion(createdBook.getVersion() - 1);
        //when
        bookService.updateBook(createdBook);
    }
}
