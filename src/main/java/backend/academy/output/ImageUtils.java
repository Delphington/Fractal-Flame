package backend.academy.output;

import backend.academy.model.FractalImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public final class ImageUtils {

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        int width = image.width();
        int height = image.height();

        BufferedImage images = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Заполнение изображения данными из массива RGB
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                images.setRGB(x, y,
                    new Color(image.pixel(x, y).r(), image.pixel(x, y).g(), image.pixel(x, y).b()).getRGB());
            }
        }

        try {
            File outputFile = new File(filename + "." + format.format());
            ImageIO.write(images, format.format(), outputFile);
        } catch (IOException e) {
            System.err.println("Файл не получилось сохранить");
            log.error("Не получилось сохранить файл");
        }
    }
}
