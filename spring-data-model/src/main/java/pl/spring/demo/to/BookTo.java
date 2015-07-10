package pl.spring.demo.to;


import java.util.ArrayList;
import java.util.List;

public class BookTo {
    private long id;
    private String title;

    private List<AuthorTo> authors = new ArrayList<>();

    public BookTo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorTo> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorTo> authors) {
        this.authors = authors;
    }
}
