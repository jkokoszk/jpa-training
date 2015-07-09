package pl.spring.demo.entity;

import pl.spring.demo.type.PersonalData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTHOR")
@DiscriminatorColumn(name = "TYPE", length = 6, discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AuthorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME", nullable = false))})
    protected PersonalData personalData;


    // for hibernate
    protected AuthorEntity() {

    }

    protected AuthorEntity(PersonalData personalData) {
        this.personalData = personalData;
    }

    protected AuthorEntity(Long id, PersonalData personalData) {
        this(personalData);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
