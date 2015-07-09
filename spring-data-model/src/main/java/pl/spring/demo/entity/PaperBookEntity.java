package pl.spring.demo.entity;

import pl.spring.demo.type.BookCover;
import pl.spring.demo.type.PaperSize;

import javax.persistence.*;

@Entity
@Table(name = "PAPER_BOOK")
@PrimaryKeyJoinColumn(name = "book_id", referencedColumnName = "id")
public class PaperBookEntity extends BookExemplarEntity {

    private int pagesCount;

    @Enumerated(EnumType.STRING)
    private PaperSize paperSize;

    @Enumerated(EnumType.STRING)
    private BookCover bookCover;

    // for hibernate
    protected PaperBookEntity() {
        super();
    }

    public PaperBookEntity(String serialNumber, boolean available, int pagesCount, PaperSize paperSize, BookCover bookCover) {
        this(null, serialNumber, available, pagesCount, paperSize, bookCover);
    }

    public PaperBookEntity(Long id, String serialNumber, boolean available, int pagesCount, PaperSize paperSize, BookCover bookCover) {
        super(id, serialNumber, available);
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
