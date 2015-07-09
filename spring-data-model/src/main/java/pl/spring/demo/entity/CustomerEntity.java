package pl.spring.demo.entity;

import pl.spring.demo.type.PersonalData;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME", nullable = false))})
    private PersonalData personalData;

    @Column(length = 15, nullable = false)
    private String phoneNumber;
    @Column(length = 35, nullable = false)
    private String email;

    @OneToMany(mappedBy = "customerEntity")
    private Set<LoanEntity> loans;

    // for hibernate
    protected CustomerEntity() {

    }

    public CustomerEntity(PersonalData personalData, String phoneNumber, String email) {
        this.personalData = personalData;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerEntity(Long id, PersonalData personalData, String phoneNumber, String email) {
        this(personalData, phoneNumber, email);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Set<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(Set<LoanEntity> loans) {
        this.loans = loans;
    }
}
