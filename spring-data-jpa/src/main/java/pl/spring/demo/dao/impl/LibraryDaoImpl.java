package pl.spring.demo.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pl.spring.demo.dao.LibraryDao;
import pl.spring.demo.entity.Library;

@Repository
public class LibraryDaoImpl extends AbstractDao<Library, Long> implements LibraryDao{

	@Override
	public List<Library> findByName(String name) {
		Query query = entityManager.createQuery("SELECT library FROM Library library "
				+ "WHERE upper(library.name) like concat(upper(:name), '%')", Library.class);
		query.setParameter("name",  name);
		return query.getResultList();
	}

	@Override
	public List<Library> findLibrariesByAuthor(String lastName) {
		Query query = entityManager.createQuery("SELECT library FROM Library library "
				+ "JOIN FETCH library.books book "
				+ "JOIN FETCH book.authors author "
				+ "WHERE author.personalData.lastName =:lastName", Library.class);
		query.setParameter("lastName", lastName);
		return query.getResultList();
	}
}
