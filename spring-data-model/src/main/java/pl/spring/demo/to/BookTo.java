package pl.spring.demo.to;


public class BookTo {
    private long id;
    private String title;

    // for hibernate
    protected BookTo() {
    }

    public BookTo(String title) {
        this.title = title;
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

}
