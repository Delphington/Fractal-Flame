package backend.academy.post;

import backend.academy.model.FractalImage;
import backend.academy.model.Pixel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GammaCorrection {
    public static final double GAMMA = 2.2;

    public static void gammaCorrection(FractalImage image) {
        double max = 0.0;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.cnt() != 0) {
                    double norm = Math.log10(pixel.cnt());
                    if (max < norm) {
                        max = norm;
                    }
                }
            }
        }

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.cnt() != 0) {
                    double normal = Math.log10(pixel.cnt()) / max;
                    image.data()[y][x] = new Pixel(
                        (int) (pixel.r() * Math.pow(normal, (1.0 / GAMMA))),
                        (int) (pixel.g() * Math.pow(normal, (1.0 / GAMMA))),
                        (int) (pixel.b() * Math.pow(normal, (1.0 / GAMMA))),
                        pixel.cnt()
                    );
                }
            }
        }
    }
}
