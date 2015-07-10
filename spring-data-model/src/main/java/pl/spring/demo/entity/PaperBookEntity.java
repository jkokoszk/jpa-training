package pl.spring.demo.entity;

import pl.spring.demo.type.BookCover;
import pl.spring.demo.type.PaperSize;

import javax.persistence.*;

@Entity
@Table(name = "PAPER_BOOK")
@PrimaryKeyJoinColumn(name = "book_ex_id", referencedColumnName = "id")
public class PaperBookEntity extends BookExemplarEntity {

    private int pagesCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaperSize paperSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCover bookCover;

    // for hibernate
    protected PaperBookEntity() {
        super();
    }

    public PaperBookEntity(String serialNumber, int pagesCount, PaperSize paperSize, BookCover bookCover) {
        this(null, serialNumber, pagesCount, paperSize, bookCover);
    }

    public PaperBookEntity(Long id, String serialNumber, int pagesCount, PaperSize paperSize, BookCover bookCover) {
        super(id, serialNumber);
        this.pagesCount = pagesCount;
        this.paperSize = paperSize;
        this.bookCover = bookCover;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public BookCover getBookCover() {
        return bookCover;
    }
}
