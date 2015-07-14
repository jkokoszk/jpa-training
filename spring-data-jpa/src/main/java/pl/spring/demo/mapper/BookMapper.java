package pl.spring.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.spring.demo.dao.AuthorDao;
import pl.spring.demo.entity.*;
import pl.spring.demo.to.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper extends AbstractMapper<BookEntity, BookTo> {

    @Autowired
    private AuthorDao authorDao;

    @Override
    public BookTo mapSource(BookEntity source) {
        BookTo book = null;
        if (source != null) {
            book = new BookTo();
            book.setId(source.getId());
            book.setTitle(source.getTitle());
            book.setAuthors(new ArrayList<>(new AuthorMapper().mapSourceCollection(source.getAuthors())));
        }
        return book;
    }

    @Override
    public BookEntity mapTarget(BookTo target) {
        BookEntity book = null;
        if (target != null) {
            book = new BookEntity(target.getId(), target.getTitle());
            book.setAuthors(new HashSet<>(new AuthorMapper().mapTargetCollection(target.getAuthors())));
        }
        return book;
    }

    public BookEntity mapNewBook(NewBookTo newBook) {
        if (newBook == null) {
            return null;
        }

        BookEntity book = new BookEntity(newBook.getTitle());

        BookSpoilerEntity bookSpoiler = mapBookSpoiler(null, newBook.getSpoiler());
        book.setBookSpoiler(bookSpoiler);

        Set<AuthorEntity> bookAuthors = mapAuthors(newBook.getAuthors());
        book.setAuthors(bookAuthors);

        Set<BookExemplarEntity> bookExemplars = mapBookExemplars(newBook.getExemplars());
        book.setBookExemplars(bookExemplars);
        return book;
    }

    private BookSpoilerEntity mapBookSpoiler(Long id, String content) {
        if (id != null || !StringUtils.isEmpty(content)) {
            return new BookSpoilerEntity(id, content);
        }
        return null;
    }

    private Set<AuthorEntity> mapAuthors(Collection<Long> authorIds) {
        if (!authorIds.isEmpty()) {
            return authorIds.stream().map(this::findAuthorReference).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    private AuthorEntity findAuthorReference(long authorId) {
        return authorDao.getOne(authorId);
    }

    private Set<BookExemplarEntity> mapBookExemplars(Set<? extends BookExemplarTo> exemplars) {
        if (!exemplars.isEmpty()) {
            return exemplars.stream().map(this::mapBookExemplar).collect(Collectors.toSet());
        }
        return null;
    }

    private BookExemplarEntity mapBookExemplar(BookExemplarTo bookExemplarTo) {
        if (bookExemplarTo instanceof PaperBookExemplarTo) {
            return mapPaperBookExemplarTo((PaperBookExemplarTo) bookExemplarTo);
        }
        else if (bookExemplarTo instanceof AudioBookExemplarTo) {
            return mapAudioBookExemplarTo((AudioBookExemplarTo) bookExemplarTo);
        }
        return null;
    }

    private PaperBookExemplarEntity mapPaperBookExemplarTo(PaperBookExemplarTo bookExemplarTo) {
        return new PaperBookExemplarEntity(bookExemplarTo.getId(), bookExemplarTo.getSerialNumber(),
                bookExemplarTo.getPagesCount(), bookExemplarTo.getPaperSize(), bookExemplarTo.getBookCover());
    }

    private AudioBookExemplarEntity mapAudioBookExemplarTo(AudioBookExemplarTo bookExemplarTo) {
        return new AudioBookExemplarEntity(bookExemplarTo.getId(), bookExemplarTo.getSerialNumber(), bookExemplarTo.getFormat());
    }
}
