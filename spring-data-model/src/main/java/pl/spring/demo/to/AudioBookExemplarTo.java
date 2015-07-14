package pl.spring.demo.to;

import pl.spring.demo.type.AudioBookFormat;

public class AudioBookExemplarTo extends BookExemplarTo {

    private AudioBookFormat format;

    public AudioBookFormat getFormat() {
        return format;
    }

    public void setFormat(AudioBookFormat format) {
        this.format = format;
    }
}
