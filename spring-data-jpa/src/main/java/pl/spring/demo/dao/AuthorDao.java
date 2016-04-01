package pl.spring.demo.dao;

import pl.spring.demo.entity.Author;

import java.util.List;

public interface AuthorDao extends Dao<Author, Long> {

    public <T extends Author> List<T> findAllAuthors(Class<T> clazz);
}
