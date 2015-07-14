package pl.spring.demo.to;

import pl.spring.demo.type.BookCover;
import pl.spring.demo.type.PaperSize;

public class PaperBookExemplarTo extends BookExemplarTo {

    private int pagesCount;
    private PaperSize paperSize;
    private BookCover bookCover;

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(PaperSize paperSize) {
        this.paperSize = paperSize;
    }

    public BookCover getBookCover() {
        return bookCover;
    }

    public void setBookCover(BookCover bookCover) {
        this.bookCover = bookCover;
    }
}
