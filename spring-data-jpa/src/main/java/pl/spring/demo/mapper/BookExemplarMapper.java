package pl.spring.demo.mapper;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.AudioBookExemplarEntity;
import pl.spring.demo.entity.BookExemplarEntity;
import pl.spring.demo.entity.PaperBookExemplarEntity;
import pl.spring.demo.to.AudioBookExemplarTo;
import pl.spring.demo.to.BookExemplarTo;
import pl.spring.demo.to.PaperBookExemplarTo;

@Component
public class BookExemplarMapper extends AbstractMapper<BookExemplarEntity, BookExemplarTo> {

    @Override
    public BookExemplarTo mapSource(BookExemplarEntity source) {

        if (isObjectOfClass(source, AudioBookExemplarEntity.class)) {
            return mapAudioBookExemplar((AudioBookExemplarEntity) HibernateProxyHelper.unwrap(source));
        }
        else if (isObjectOfClass(source, PaperBookExemplarEntity.class)) {
            return mapPaperBookExemplar((PaperBookExemplarEntity) HibernateProxyHelper.unwrap(source));
        }
        else {
            throw new IllegalArgumentException("Not supported mapping for class " + source.getClass().getName());
        }
    }

    private AudioBookExemplarTo mapAudioBookExemplar(AudioBookExemplarEntity source) {
        return new AudioBookExemplarTo(source.getId(), source.getSerialNumber(), source.getFormat());
    }

    private PaperBookExemplarTo mapPaperBookExemplar(PaperBookExemplarEntity source) {
        return new PaperBookExemplarTo(source.getId(), source.getSerialNumber(), source.getPagesCount(), source.getPaperSize(), source.getBookCover());
    }

    private boolean isObjectOfClass(Object object, Class<?> clazz) {
        return object != null && Hibernate.getClass(object) == clazz;
    }

    @Override
    public BookExemplarEntity mapTarget(BookExemplarTo target) {
        // TODO Implement
        return null;
    }
}
