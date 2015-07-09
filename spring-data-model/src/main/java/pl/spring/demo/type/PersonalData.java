package pl.spring.demo.type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class PersonalData {

    private String firstName;

    private String lastName;

    @Column(columnDefinition="DATE", nullable = false)
    private Date birthDate;

    public PersonalData(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
