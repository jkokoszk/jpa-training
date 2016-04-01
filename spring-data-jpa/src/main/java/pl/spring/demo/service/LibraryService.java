package pl.spring.demo.service;

import java.util.Collection;

import pl.spring.demo.to.LibraryTo;

public interface LibraryService {
	Collection<LibraryTo> findByName(String name);
	LibraryTo findLibraryById(long id);
	LibraryTo createNewLibrary(LibraryTo library);
	void changeLibraryName(long id, String newName);
	void removeLibrary(long id);
	void addBookToLibrary(long libraryId, long bookId);
	Collection<LibraryTo> findLibrariesByAuthor(String lastName);
}
