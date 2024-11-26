package backend.academy;

import backend.academy.config.ImageUtils;
import backend.academy.input.InputSimpleParam;
import backend.academy.post.GammaCorrection;
import backend.academy.render.MultiThreadRender;
import backend.academy.render.Rect;
import backend.academy.render.Renderer;
import backend.academy.render.SingleThreadRender;
import backend.academy.transformation.Transformation;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

//todo: Логирование
//todo: вывод заполненных данных

@Log4j2
@UtilityClass
public class StartProgram {

    private final static double RECT_X = -2.5;
    private final static double RECT_Y = -2.5;
    private final static double RECT_WIDTH = 5.8;
    private final static double RECT_HEIGHT = 5.2;

    private InputSimpleParam srvInput;
    private FractalImage fractalImage;
    private Transformation transformation;
    private List<Space> spaces;
    private Renderer render;

    public static void start() {

        srvInput = new InputSimpleParam();
        srvInput.input();
        srvInput.printInputConfig();

        fractalImage = FractalImage.create(srvInput.weight(), srvInput.height());
        transformation = srvInput.variousTransformation().get(srvInput.typeTransformation());
        spaces = srvInput.spaces();

        if (srvInput.threadCount() == 1) {
            render = new SingleThreadRender();
            log.info("однопоточная версия");
        } else {
            render = new MultiThreadRender(srvInput.threadCount());
            log.info("многопоточная версия");
        }

        render.rend(
            fractalImage,
            spaces,
            transformation,
            srvInput.sampleCount(),
            srvInput.iterationCount(),
            srvInput.symmetryCount(),
            new Rect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT)
        );

        GammaCorrection.gammaCorrection(fractalImage);

        // Сохранение уменьшенного изображения в файл
        ImageUtils.save(fractalImage, Path.of("src/main/resources/Image/generate/img"), srvInput.imageFormat());
    }
}
