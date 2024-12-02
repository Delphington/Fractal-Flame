import backend.academy.input.InputSimpleParam;
import backend.academy.model.FractalImage;
import backend.academy.model.Space;
import backend.academy.render.MultiThreadRender;
import backend.academy.render.Rect;
import backend.academy.render.Render;
import backend.academy.render.SingleThreadRender;
import backend.academy.transformation.Transformation;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StartProgramTest {

    private long start;
    private long end;
    private long resultTimeUser = 2;
    private long resultCompareTime = 1;

    private final double RECT_X = -2.5;
    private final double RECT_Y = -2.5;
    private final double RECT_WIDTH = 5.8;
    private final double RECT_HEIGHT = 5.2;
    private final int DEFAULT_COUNT_THREAD = 10;

    private InputSimpleParam srvInput;
    private FractalImage fractalImage;
    private Transformation transformation;
    private List<Space> spaces;
    private Render render;
    private Render compareRender;

    @BeforeEach
    public void setInit() {
        srvInput = new InputSimpleParam();

    }

    @Test
    @DisplayName("Тест, где пользовать задает единственный поток")
    public void startSingleTest() {

        srvInput.threadCount(1);

        fractalImage = FractalImage.create(srvInput.weight(), srvInput.height());
        transformation = srvInput.variousTransformation().get(srvInput.typeTransformation());
        spaces = srvInput.spaces();

        render = new SingleThreadRender();
        compareRender = new MultiThreadRender(DEFAULT_COUNT_THREAD);

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
        System.err.println(resultTimeUser);
        System.err.println(resultCompareTime);
        assertTrue((resultCompareTime - resultTimeUser) > 0);

    }

    //
    @Test
    @DisplayName("Тест, где пользовать множество потоков")
    public void startMultiTest() {
        srvInput.threadCount(8);
        srvInput.printInputConfig();

        fractalImage = FractalImage.create(srvInput.weight(), srvInput.height());
        transformation = srvInput.variousTransformation().get(srvInput.typeTransformation());
        spaces = srvInput.spaces();

        render = new MultiThreadRender(srvInput.threadCount());
        compareRender = new SingleThreadRender();

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

        assertTrue((resultTimeUser - resultCompareTime) > 0);
    }
}
