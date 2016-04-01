package pl.spring.demo.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.LibraryDao;
import pl.spring.demo.entity.Library;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookSearchCriteriaTo;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.NewBookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceImplTest extends AbstractDatabaseTest {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private LibraryDao libraryDao;

//    @Test
//    public void findBooksShouldFindBooksByTitle() {
//        // given
//        String title = "Wszyscy mamy";
//        BookSearchCriteriaTo searchCriteria = new BookSearchCriteriaTo();
//        searchCriteria.setTitle(title);
//        // when
//        List<BookTo> books = bookService.findBooks(searchCriteria);
//        // then
//        assertEquals(1, books.size());
//    }
//
//    @Test
//    public void findBooksShouldNotFindBooksByNotExistingTitle() {
//        // given
//        String title = "NotExistingTitle123";
//        BookSearchCriteriaTo searchCriteria = new BookSearchCriteriaTo();
//        searchCriteria.setTitle(title);
//        // when
//        List<BookTo> books = bookService.findBooks(searchCriteria);
//        // then
//        assertEquals(0, books.size());
//    }
//
//    @Test
//    public void findBooksShouldFindBooksByAuthor() {
//        // given
//        String author = "Popuelin";
//        BookSearchCriteriaTo searchCriteria = new BookSearchCriteriaTo();
//        searchCriteria.setAuthor(author);
//        // when
//        List<BookTo> books = bookService.findBooks(searchCriteria);
//        // then
//        assertEquals(4, books.size());
//    }
//
//    @Test
//    public void findBooksShouldFindBooksWithSpoiler() {
//        // given
//        BookSearchCriteriaTo searchCriteria = new BookSearchCriteriaTo();
//        searchCriteria.setHasReview(true);
//        // when
//        List<BookTo> books = bookService.findBooks(searchCriteria);
//        // then
//        assertEquals(1, books.size());
//    }
//
//    @Test
//    public void findBooksShouldFindBooksWithoutSpoiler() {
//        // given
//        BookSearchCriteriaTo searchCriteria = new BookSearchCriteriaTo();
//        searchCriteria.setHasReview(false);
//        // when
//        List<BookTo> books = bookService.findBooks(searchCriteria);
//        // then
//        assertEquals(7, books.size());
//    }

    @Test
    public void shouldFindBookById(){
    	// given
    	Library library = new Library();
    	library.setName("Library 1");
    	libraryDao.save(library);
    	
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setLibraryId(library.getId());
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
    	Library library = new Library();
    	library.setName("Library 1");
    	Library savedLibrary = libraryDao.save(library);
    	
        NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        bookToSave.setLibraryId(savedLibrary.getId());
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

//    @Test
//    public void testShouldRemoveBookWithAllExemplarsAndSpoiler() {
//        // given
//        final long bookIdToRemove = 1L;
//        assertFalse(bookService.findBookExemplars(bookIdToRemove).isEmpty());
//        assertNotNull(bookService.findBookReview(bookIdToRemove));
//        // when
//        bookService.removeBookById(bookIdToRemove);
//        // then
//        assertNull(bookService.findBookById(bookIdToRemove));
//        assertNull(bookService.findBookReview(bookIdToRemove));
//        assertTrue(bookService.findBookExemplars(bookIdToRemove).isEmpty());
//    }

}
