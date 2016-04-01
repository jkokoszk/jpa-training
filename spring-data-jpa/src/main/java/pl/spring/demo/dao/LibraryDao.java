package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.entity.Library;

public interface LibraryDao extends Dao<Library, Long>{

	public List<Library> findByName(String name);

	public List<Library> findLibrariesByAuthor(String lastName);
}
