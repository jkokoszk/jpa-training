package pl.spring.demo.to;

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
}
