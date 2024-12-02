package backend.academy.output;

import lombok.Getter;

@Getter
public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");

    private final String format;

    ImageFormat(String format) {
        this.format = format;
    }
}

