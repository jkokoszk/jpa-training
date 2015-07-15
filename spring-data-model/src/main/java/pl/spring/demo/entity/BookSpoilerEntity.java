package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "BOOK_SPOILER")
public class BookSpoilerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @OneToOne(optional = false)
    @JoinColumn(name = "BOOK_FK", updatable = false, nullable = false)
    private BookEntity book;

    // for hibernate
    protected BookSpoilerEntity() {
    }

    public BookSpoilerEntity(String content) {
        this.content = content;
    }

    public BookSpoilerEntity(Long id, String content) {
        this(content);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
