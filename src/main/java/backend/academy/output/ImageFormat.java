package backend.academy.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");
    private final String format;
}

