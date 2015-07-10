package pl.spring.demo.dao;

import pl.spring.demo.entity.BookExemplarEntity;

public interface BookExemplarDao extends Dao<BookExemplarEntity, Long> {

    boolean isBookExemplarBorrowed(long bookExemplarId);

}
