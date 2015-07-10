package pl.spring.demo.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, K extends Serializable> {

    void save(T entity);

    T getReference(K id);

    T find(K id);

    List<T> findAll();

    T update(T entity);

    void delete(T entity);

    void delete(K id);

    void deleteAll();

    long count();

    boolean exists(K id);
}
