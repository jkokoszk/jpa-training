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
		Query query = entityManager.createQuery("SELECT distinct o FROM Library library "
				+ "WHERE library.name like :name", Library.class);
		query.setParameter("name",  name);
		return query.getResultList();
	}
}
