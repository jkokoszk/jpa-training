package pl.spring.demo.to;

import pl.spring.demo.type.LiteraryGenre;

public class WriterTo extends AuthorTo {

    private LiteraryGenre genre;

    public LiteraryGenre getGenre() {
        return genre;
    }

    public void setGenre(LiteraryGenre genre) {
        this.genre = genre;
    }
}
