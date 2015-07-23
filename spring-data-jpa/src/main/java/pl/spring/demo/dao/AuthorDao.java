package pl.spring.demo.dao;

import pl.spring.demo.entity.AuthorEntity;

import java.util.List;

public interface AuthorDao extends Dao<AuthorEntity, Long> {

    public <T extends AuthorEntity> List<T> findAllAuthors(Class<T> clazz);
}
