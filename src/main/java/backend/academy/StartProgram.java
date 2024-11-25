package backend.academy;

import backend.academy.config.ImageUtils;
import backend.academy.input.InputSimpleParam;
import backend.academy.post.GammaCorrection;
import backend.academy.render.Rect;
import backend.academy.render.RenderSingleThread;
import backend.academy.transformation.Transformation;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.UtilityClass;

//todo: Логирование
//todo: вывод заполненных данных

@UtilityClass
public class StartProgram {
    private final static double RECT_X = -2.5;
    private final static double RECT_Y = -2.5;
    private final static double RECT_WIDTH = 5.8;
    private final static double RECT_HEIGHT = 5.2;

    public static void start() {

        InputSimpleParam srvInput = new InputSimpleParam();
        srvInput.input();
        srvInput.printInputConfig();

        FractalImage fractalImage = FractalImage.create(srvInput.weight(), srvInput.height());
        Transformation transformations1 = srvInput.variousTransformation().get(srvInput.typeTransformation());
        List<Space> spaces = srvInput.spaces();
        RenderSingleThread render = new RenderSingleThread();

        render.rend(
            fractalImage,
            spaces,
            transformations1,
            srvInput.sampleCount(),
            srvInput.iterationCount(),
            srvInput.symmetryCount(),
            new Rect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT)
        );

        GammaCorrection.gammaCorrection(fractalImage);

        // Сохранение уменьшенного изображения в файл
        ImageUtils.save(fractalImage, Path.of("src/main/resources/img"), srvInput.imageFormat());
    }
}
