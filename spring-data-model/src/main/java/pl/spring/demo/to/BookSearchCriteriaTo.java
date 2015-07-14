package pl.spring.demo.to;

import com.google.common.base.Objects;

public class BookSearchCriteriaTo {

    private String title;
    private String author;
    private Boolean hasEBook;
    private Boolean available;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getHasEBook() {
        return hasEBook;
    }

    public void setHasEBook(Boolean hasEBook) {
        this.hasEBook = hasEBook;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookSearchCriteriaTo that = (BookSearchCriteriaTo) o;

        return Objects.equal(author, that.author)
                && Objects.equal(available, that.available)
                && Objects.equal(hasEBook, that.hasEBook)
                && Objects.equal(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(author, available, hasEBook, title);
    }
}
