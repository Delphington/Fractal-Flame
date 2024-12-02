package backend.academy.render;

import backend.academy.model.FractalImage;
import backend.academy.model.Space;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MultiThreadRender implements Render {

    private final ExecutorService executorService;

    public MultiThreadRender(int countThread) {
        executorService = Executors.newFixedThreadPool(countThread);
    }

    @Override
    public void rend(
        FractalImage fractalImage,
        List<Space> spaces,
        Transformation transformation,
        int numberSamples,
        int iterationSample,
        int symmetry,
        Rect rect
    ) {

        for (int num = 0; num < numberSamples; num++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    transformPoint(fractalImage, new SrvRandomEnvironment(spaces, transformation), symmetry,
                        iterationSample, rect);
                }
            };

            executorService.execute(task);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Ошибка при выволнение многопоточности: " + e.getStackTrace());
            throw new RuntimeException(e);

        }

    }
}
