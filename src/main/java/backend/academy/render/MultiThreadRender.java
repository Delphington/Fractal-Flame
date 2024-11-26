package backend.academy.render;

import backend.academy.FractalImage;
import backend.academy.Space;
import backend.academy.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//todo: поменять имя transformPoint

public class MultiThreadRender implements Renderer {

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
            throw new RuntimeException(e);
        }

    }
}
