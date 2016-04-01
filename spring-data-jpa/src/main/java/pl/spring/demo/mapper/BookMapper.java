package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import pl.spring.demo.dao.AuthorDao;
import pl.spring.demo.dao.LibraryDao;
import pl.spring.demo.entity.Author;
import pl.spring.demo.entity.Book;
import pl.spring.demo.entity.BookReview;
import pl.spring.demo.entity.Library;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.NewBookTo;

@Component
public class BookMapper extends AbstractMapper<Book, BookTo> {

    private final AuthorDao authorDao;
    private final LibraryDao libraryDao;

    @Autowired
    public BookMapper(AuthorDao authorDao, LibraryDao libraryDao) {
        this.authorDao = authorDao;
        this.libraryDao = libraryDao;
    }

    @Override
    public BookTo convertToBookTo(Book source) {
        BookTo book = null;
        if (source != null) {
            book = new BookTo();
            book.setId(source.getId());
            book.setTitle(source.getTitle());
            book.setAuthors(new ArrayList<>(new AuthorMapper().mapSourceCollection(source.getAuthors())));
            book.setVersion(source.getVersion());
        }
        return book;
    }

    @Override
    public Book convertToBook(BookTo target) {
        Book book = null;
        if (target != null) {
            book = new Book(target.getId(), target.getTitle());
            book.setAuthors(new HashSet<>(new AuthorMapper().mapTargetCollection(target.getAuthors())));
            book.setVersion(target.getVersion());
        }
        return book;
    }

    public Book mapNewBook(NewBookTo newBook) {
        if (newBook == null) {
            return null;
        }

        Book book = new Book(newBook.getTitle());

        BookReview bookReview = mapBookReview(null, newBook.getReview());
        book.setBookReview(bookReview);

        Set<Author> bookAuthors = mapAuthors(newBook.getAuthors());
        book.setAuthors(bookAuthors);
        book.setLibrary(mapLibrary(newBook.getLibraryId()));
        return book;
    }

    private BookReview mapBookReview(Long id, String content) {
        if (id != null || !StringUtils.isEmpty(content)) {
            return new BookReview(id, content);
        }
        return null;
    }
    private Library mapLibrary(Long libraryId) {
    	if (libraryId != null) {
    		return findLibraryReference(libraryId);
    	}
    	return null;
    }

    private Set<Author> mapAuthors(Collection<Long> authorIds) {
        if (!authorIds.isEmpty()) {
            return authorIds.stream().map(this::findAuthorReference).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    private Author findAuthorReference(long authorId) {
        return authorDao.getOne(authorId);
    }
    private Library findLibraryReference(long libraryId) {
    	return libraryDao.getOne(libraryId);
    }
}
