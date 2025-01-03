package backend.academy;

import backend.academy.input.InputSimpleParam;
import backend.academy.model.FractalImage;
import backend.academy.model.Space;
import backend.academy.output.ImageUtils;
import backend.academy.post.GammaCorrection;
import backend.academy.render.MultiThreadRender;
import backend.academy.render.Rect;
import backend.academy.render.Render;
import backend.academy.render.SingleThreadRender;
import backend.academy.transformation.Transformation;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@UtilityClass
public class StartProgram {

    private final PrintStream printStream = System.out;

    private long start;
    private long end;
    private long resultTimeUser;
    private long resultCompareTime;

    private final static double RECT_X = -2.5;
    private final static double RECT_Y = -2.5;
    private final static double RECT_WIDTH = 5.8;
    private final static double RECT_HEIGHT = 5.2;
    private final static int DEFAULT_COUNT_THREAD = 10;

    private InputSimpleParam srvInput;
    private FractalImage fractalImage;
    private Transformation transformation;
    private List<Space> spaces;
    private Render render;
    private Render compareRender;

    public static void input() {
        srvInput = new InputSimpleParam();
        InputSimpleParam.inputSrv(srvInput);

        srvInput.input();
        srvInput.printInputConfig();

        fractalImage = FractalImage.create(srvInput.weight(), srvInput.height());
        transformation = srvInput.typeTransformation();
        spaces = srvInput.spaces();

        if (srvInput.threadCount() == 1) {
            render = new SingleThreadRender();
            compareRender = new MultiThreadRender(DEFAULT_COUNT_THREAD);
            log.info("Однопоточная версия");
        } else {
            render = new MultiThreadRender(srvInput.threadCount());
            compareRender = new SingleThreadRender();
            log.info("Многопоточная версия");
        }
    }

    public static void run() {
        start = System.nanoTime();
        render.rend(
            fractalImage,
            spaces,
            transformation,
            srvInput.sampleCount(),
            srvInput.iterationCount(),
            srvInput.symmetryCount(),
            new Rect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT)
        );
        end = System.nanoTime();

        resultTimeUser = end - start;

        start = System.nanoTime();
        compareRender.rend(
            fractalImage,
            spaces,
            transformation,
            srvInput.sampleCount(),
            srvInput.iterationCount(),
            srvInput.symmetryCount(),
            new Rect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT)
        );
        end = System.nanoTime();
        resultCompareTime = end - start;

        GammaCorrection.gammaCorrection(fractalImage);

        // Сохранение уменьшенного изображения в файл
        ImageUtils.save(fractalImage, Path.of("src/main/resources/Image/generate/img"), srvInput.imageFormat());
        printStream.println("Картинка успешно сохранена!");
    }

    @SuppressWarnings("MultipleStringLiterals")
    public void printCompareThread() {
        printStream.println("=====================================");
        printStream.printf("Вы выбрали количество потоков: %d \n", srvInput.threadCount());
        if (srvInput.threadCount() == 1) {
            printStream.printf("Время работы однопоточного: %d \n", resultTimeUser);
            printStream.printf("Время работы %d поточного: %d \n", DEFAULT_COUNT_THREAD, resultCompareTime);
            printStream.printf("Разница однопоточки и многопоточки: %d \n", (resultCompareTime - resultTimeUser));

        } else {
            printStream.printf("Время работы однопоточного: %d \n", resultCompareTime);
            printStream.printf("Время работы %d поточного: %d \n", srvInput.threadCount(), resultTimeUser);
            printStream.printf("Разница однопоточки и многопоточки: %d \n", resultTimeUser - resultCompareTime);
        }
        printStream.println("Если число < 0  -> многопоточная версия работает быстрее");
    }

}
