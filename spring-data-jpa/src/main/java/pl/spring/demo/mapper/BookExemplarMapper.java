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
        AudioBookExemplarTo audioBookExemplarTo = new AudioBookExemplarTo();
        mapBookExemplarEntity(source, audioBookExemplarTo);
        audioBookExemplarTo.setFormat(source.getFormat());
        return audioBookExemplarTo;
    }

    private PaperBookExemplarTo mapPaperBookExemplar(PaperBookExemplarEntity source) {
        PaperBookExemplarTo paperBookExemplarTo = new PaperBookExemplarTo();
        mapBookExemplarEntity(source, paperBookExemplarTo);
        paperBookExemplarTo.setBookCover(source.getBookCover());
        paperBookExemplarTo.setPagesCount(source.getPagesCount());
        paperBookExemplarTo.setPaperSize(source.getPaperSize());
        return paperBookExemplarTo;
    }

    private boolean isObjectOfClass(Object object, Class<?> clazz) {
        return object != null && Hibernate.getClass(object) == clazz;
    }

    private void mapBookExemplarEntity(BookExemplarEntity entity, BookExemplarTo to) {
        to.setId(entity.getId());
        to.setSerialNumber(entity.getSerialNumber());
    }

    @Override
    public BookExemplarEntity mapTarget(BookExemplarTo target) {
        // TODO Implement
        return null;
    }
}
