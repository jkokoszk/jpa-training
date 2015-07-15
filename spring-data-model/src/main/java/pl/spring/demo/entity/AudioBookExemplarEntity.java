package pl.spring.demo.entity;

import pl.spring.demo.type.AudioBookFormat;

import javax.persistence.*;

@Entity
@Table(name = "AUDIO_BOOK")
@PrimaryKeyJoinColumn(name = "book_ex_id", referencedColumnName = "id")
public class AudioBookExemplarEntity extends BookExemplarEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AudioBookFormat format;

    // for hibernate
    protected AudioBookExemplarEntity() {
        super();
    }

    public AudioBookExemplarEntity(String serialNumber, AudioBookFormat format) {
        this(null, serialNumber, format);
    }

    public AudioBookExemplarEntity(Long id, String serialNumber, AudioBookFormat format) {
        super(id, serialNumber);
        this.format = format;
    }

    public AudioBookFormat getFormat() {
        return format;
    }
}
