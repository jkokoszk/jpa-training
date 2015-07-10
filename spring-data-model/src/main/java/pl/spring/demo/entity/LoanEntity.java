package pl.spring.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LOAN")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date loanDate;

    @OneToMany(mappedBy = "loan")
    private Set<BookExemplarEntity> bookExemplars = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_FK", nullable = true)
    private CustomerEntity customerEntity;

    //for hibernate
    protected LoanEntity() {

    }

    public LoanEntity(Date loanDate) {
        this.loanDate = loanDate;
    }

    public LoanEntity(Long id) {
        this.id = id;
    }

    public Set<BookExemplarEntity> getBookExemplars() {
        return bookExemplars;
    }

    public void setBookExemplars(Set<BookExemplarEntity> bookExemplars) {
        this.bookExemplars = bookExemplars;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public Long getId() {
        return id;
    }

    public Date getLoanDate() {
        return loanDate;
    }
}
