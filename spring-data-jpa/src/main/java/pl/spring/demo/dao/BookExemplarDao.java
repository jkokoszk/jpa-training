package pl.spring.demo.dao;

import pl.spring.demo.entity.BookExemplarEntity;

import java.util.Collection;

public interface BookExemplarDao extends Dao<BookExemplarEntity, Long> {

    boolean isBookExemplarBorrowed(long bookExemplarId);

    Collection<BookExemplarEntity> findAllBookExemplars(long bookId);

}
