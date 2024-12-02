import backend.academy.model.FractalImage;
import backend.academy.output.ImageFormat;
import backend.academy.output.ImageUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ImageUtilsTest {

    @Test
    void saveImageTest() {
        int width = 100;
        int height = 100;
        Path tempDir = Path.of("src/main/resources/Image/generate");

        FractalImage image = FractalImage.create(width, height);

        BufferedImage images = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                images.setRGB(x, y,
                    new Color(image.pixel(x, y).r(), image.pixel(x, y).g(), image.pixel(x, y).b()).getRGB());
            }
        }
        Path outputFile = tempDir.resolve("test");
        ImageUtils.save(image, outputFile, ImageFormat.PNG);

        Path filePath = Paths.get(tempDir + "/test.png"); // Замените на ваш путь

        assertTrue(Files.exists(filePath));
    }
}
