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

    @Column(nullable = true, length = 30)
    protected String nickName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME", nullable = false))})
    protected PersonalData personalData;

    @Version
    protected long version;

    // for hibernate
    protected AuthorEntity() {

    }

    protected AuthorEntity(PersonalData personalData, String nickName) {
        this.personalData = personalData;
        this.nickName = nickName;
    }

    protected AuthorEntity(Long id, PersonalData personalData, String nickName, long version) {
        this(personalData, nickName);
        this.id = id;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public String getNickName() {
        return nickName;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
