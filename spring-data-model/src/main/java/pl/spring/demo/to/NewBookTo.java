package pl.spring.demo.to;

import java.util.HashSet;
import java.util.Set;

public class NewBookTo {

    private String title;
    private String spoiler;

    private Set<Long> authors = new HashSet<>();
    private Set<? extends BookExemplarTo> exemplars = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(String spoiler) {
        this.spoiler = spoiler;
    }

    public Set<Long> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Long> authors) {
        this.authors = authors;
    }

    public Set<? extends BookExemplarTo> getExemplars() {
        return exemplars;
    }

    public void setExemplars(Set<? extends BookExemplarTo> exemplars) {
        this.exemplars = exemplars;
    }
}
