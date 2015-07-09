package pl.spring.demo.entity;

import pl.spring.demo.type.LiteraryGenre;
import pl.spring.demo.type.PersonalData;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@DiscriminatorValue("WRITER")
public class WriterEntity extends AuthorEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    private LiteraryGenre literaryGenre;

    // for hibernate
    protected WriterEntity() {
        super();
    }

    protected WriterEntity(PersonalData personalData, LiteraryGenre literaryGenre) {
        this(null, personalData, literaryGenre);
    }

    protected WriterEntity(Long id, PersonalData personalData, LiteraryGenre literaryGenre) {
        super(id, personalData);
        this.literaryGenre = literaryGenre;
    }

    public LiteraryGenre getLiteraryGenre() {
        return literaryGenre;
    }
}
