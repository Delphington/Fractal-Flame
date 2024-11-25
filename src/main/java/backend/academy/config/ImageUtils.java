package backend.academy.config;

import backend.academy.FractalImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;


public final class ImageUtils {
    private ImageUtils() {
    }

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

        //todo: получше переписать и  добавить логгирование // Сохранение изображения в файл
        try {
            File outputFile = new File(filename + "." + format.format());
            ImageIO.write(images, format.format(), outputFile);
        } catch (IOException e) {
            System.err.println("Файл не получилось сохранить");
        }
    }
}
