package pl.spring.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CUSTOMER_CARD")
public class CustomerCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_FK", nullable = true)
    private CustomerEntity customerEntity;

    @Column(columnDefinition = "DATE", nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String serialNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
