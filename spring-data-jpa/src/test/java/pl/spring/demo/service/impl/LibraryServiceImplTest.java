package pl.spring.demo.service.impl;

import java.util.Collection;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.Library;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.LibraryService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.LibraryTo;
import pl.spring.demo.to.NewBookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LibraryServiceImplTest  extends AbstractDatabaseTest {

	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void shouldFindLibraryById(){
		// given
		LibraryTo libraryTo = new LibraryTo();
		libraryTo.setName("Library1");
		LibraryTo newLibrary = libraryService.createNewLibrary(libraryTo);
		// when
		LibraryTo foundLibrary = libraryService.findLibraryById(newLibrary.getId());
		// then
		Assertions.assertThat(foundLibrary).isNotNull();
		Assertions.assertThat(foundLibrary.getId()).isEqualTo(newLibrary.getId());
		Assertions.assertThat(foundLibrary.getName()).isEqualTo(newLibrary.getName());
	} 
	
	@Test
	public void shouldCreateNewLibrary(){
		// given
		LibraryTo libraryTo = new LibraryTo();
		libraryTo.setName("Library2");
		// when
		LibraryTo newLibrary = libraryService.createNewLibrary(libraryTo);
		// then
		Assertions.assertThat(newLibrary).isNotNull();
		Assertions.assertThat(newLibrary.getId()).isNotNull();
		Assertions.assertThat(newLibrary.getName()).isEqualTo(libraryTo.getName());
	}
	
	@Test
	public void shouldChangeLibraryName(){
		// given
		LibraryTo libraryTo = new LibraryTo();
		libraryTo.setName("Library1");
		LibraryTo newLibrary = libraryService.createNewLibrary(libraryTo);
		String newName = "changed library name";
		// when
		libraryService.changeLibraryName(newLibrary.getId(), newName);
		LibraryTo foundLibrary = libraryService.findLibraryById(newLibrary.getId());
		// then
		Assertions.assertThat(foundLibrary).isNotNull();
		Assertions.assertThat(foundLibrary.getId()).isEqualTo(newLibrary.getId());
		Assertions.assertThat(foundLibrary.getName()).isEqualTo(newName);
	}
	
	@Test
	public void shouldFindLibraryByName(){
		// given
		LibraryTo libraryTo1 = new LibraryTo();
		libraryTo1.setName("New Library1");
		LibraryTo library1 = libraryService.createNewLibrary(libraryTo1);
		
		LibraryTo libraryTo2 = new LibraryTo();
		libraryTo2.setName("New Library2");
		LibraryTo library2 = libraryService.createNewLibrary(libraryTo2);
		
		LibraryTo libraryTo3 = new LibraryTo();
		libraryTo3.setName("Library3");
		LibraryTo library3 = libraryService.createNewLibrary(libraryTo3);
		// when
		Collection<LibraryTo> foundLibraries = libraryService.findByName("New");
		// then
		Assertions.assertThat(foundLibraries).isNotNull();
		Assertions.assertThat(foundLibraries).contains(library1, library2);
		Assertions.assertThat(foundLibraries).doesNotContain(library3);
	} 
	
	@Test
	public void shouldAddBookToLibrary(){
		// given
		LibraryTo libraryTo = new LibraryTo();
		libraryTo.setName("New Library1");
		LibraryTo library = libraryService.createNewLibrary(libraryTo);
		
		NewBookTo bookToSave = new NewBookTo();
        bookToSave.setTitle("Title of new book");
        BookTo book = bookService.createBook(bookToSave);
		
		// when
		libraryService.addBookToLibrary(library.getId(), book.getId());
		LibraryTo libraryWithAddedBook = libraryService.findLibraryById(library.getId());
		// then
		Assertions.assertThat(libraryWithAddedBook).isNotNull();
		Assertions.assertThat(libraryWithAddedBook.getBooks()).contains(book);
	}
	
	@Test
	public void shouldRemoveLibrary(){
		// given
		LibraryTo libraryTo = new LibraryTo();
		libraryTo.setName("New Library1");
		LibraryTo library = libraryService.createNewLibrary(libraryTo);
		
		// when
		libraryService.removeLibrary(library.getId());
		LibraryTo removedLibrary = libraryService.findLibraryById(library.getId());
		// then
		Assertions.assertThat(removedLibrary).isNull();
	} 
}
