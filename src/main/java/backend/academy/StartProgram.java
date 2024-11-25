package backend.academy;

import backend.academy.config.ImageFormat;
import backend.academy.config.ImageUtils;
import backend.academy.input.InputSimpleParam;
import backend.academy.post.GammaCorrection;
import backend.academy.render.RenderSingleThread;
import backend.academy.render.Rect;
import backend.academy.transformation.Transformation;
import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

//todo: Логирование

@UtilityClass
public class StartProgram {
    public static void start() throws IOException {

        InputSimpleParam srvInput = new InputSimpleParam();
        srvInput.input();

        FractalImage fractalImage = FractalImage.create(srvInput.weight(), srvInput.height());
        Transformation transformations1 = srvInput.variousTransformation().get("Diamond");
        List<Space> spaces = srvInput.spaces();
        RenderSingleThread render = new RenderSingleThread();

        Rect rect = new Rect(-2.5, -2.4, 5.8, 5.2);

            // Рендеринг фрактального изображения с заданными параметрами
        render.rend(
            fractalImage,
            spaces,
            transformations1,
            srvInput.sampleCount(),
            srvInput.iterationCount(),
            srvInput.symmetryCount(),
            rect
        );

        GammaCorrection.gammaCorrection(fractalImage);

        ImageUtils.save(fractalImage, Path.of("src/main/resources/img"),
            ImageFormat.PNG); // Сохранение уменьшенного изображения в файл
    }
}
