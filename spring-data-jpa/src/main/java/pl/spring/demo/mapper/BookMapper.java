package pl.spring.demo.mapper;

import org.springframework.stereotype.Component;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookMapper extends AbstractMapper<BookEntity, BookTo> {

    @Override
    public BookTo mapSource(BookEntity source) {
        // TODO Implement
        return new BookTo(source.getTitle());
    }

    @Override
    public BookEntity mapTarget(BookTo target) {
        // TODO Implement
        return new BookEntity(target.getTitle());
    }
}
