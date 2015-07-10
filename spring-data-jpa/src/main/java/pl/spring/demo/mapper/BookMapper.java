package pl.spring.demo.mapper;

import org.springframework.stereotype.Component;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class BookMapper extends AbstractMapper<BookEntity, BookTo> {

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
}
