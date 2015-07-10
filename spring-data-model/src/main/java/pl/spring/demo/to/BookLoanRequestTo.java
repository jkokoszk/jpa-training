package pl.spring.demo.to;

public class BookLoanRequestTo {

    private long bookExemplarId;
    private long customerId;

    public long getBookExemplarId() {
        return bookExemplarId;
    }

    public void setBookExemplarId(long bookExemplarId) {
        this.bookExemplarId = bookExemplarId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
