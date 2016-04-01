package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.spring.demo.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
