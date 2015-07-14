package pl.spring.demo.mapper;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.WriterEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.WriterTo;
import pl.spring.demo.type.PersonalData;

public class AuthorMapper extends AbstractMapper<AuthorEntity, AuthorTo> {

    @Override
    public AuthorTo mapSource(AuthorEntity source) {
        if (isWriter(source)) {
            return mapWriter((WriterEntity) unwrap(source));
        }

        return null;
    }

    @Override
    public AuthorEntity mapTarget(AuthorTo target) {
        if (isWriter(target)) {
            return mapWriter((WriterTo) target);
        }
        return null;
    }

    private WriterTo mapWriter(WriterEntity source) {
        WriterTo writer = new WriterTo();
        mapSource(source, writer);
        writer.setGenre(source.getLiteraryGenre());
        return writer;
    }

    private WriterEntity mapWriter(WriterTo target) {
        return new WriterEntity(target.getId(), copyPersonalData(target.getPersonalData()), target.getGenre(), target.getNickName());
    }

    protected void mapSource(AuthorEntity source, AuthorTo target) {
        target.setId(source.getId());
        target.setNickName(source.getNickName());
        target.setPersonalData(copyPersonalData(source.getPersonalData()));
    }

    private PersonalData copyPersonalData(PersonalData personalDataToCopy) {
        PersonalData personalData = null;
        if (personalDataToCopy != null) {
            personalData = new PersonalData(personalDataToCopy.getFirstName(), personalDataToCopy.getLastName(), personalDataToCopy.getBirthDate());
        }
        return personalData;
    }

    private boolean isWriter(AuthorEntity entity) {
        return entity != null && Hibernate.getClass(entity) == WriterEntity.class;
    }

    private boolean isWriter(AuthorTo to) {
        return to != null && to instanceof WriterTo;
    }

    @SuppressWarnings("unchecked")
    public <T> T unwrap(T o) {
        if (o instanceof HibernateProxy)
            return (T) ((HibernateProxy)o).getHibernateLazyInitializer().getImplementation();
        else
            return o;
    }
}
