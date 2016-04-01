package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.spring.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
