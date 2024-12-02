package backend.academy.render;

import backend.academy.model.FractalImage;
import backend.academy.model.Space;
import backend.academy.transformation.Transformation;
import java.util.List;

public class SingleThreadRender implements Render {
    public void rend(
        FractalImage fractalImage,
        List<Space> spaces,
        Transformation transformation,
        int numberSamples,
        int iterationSample,
        int symmetry,
        Rect rect) {
        for (int i = 0; i < numberSamples; i++) {
            transformPoint(fractalImage, new SrvRandomEnvironment(spaces, transformation),
                symmetry, iterationSample, rect);
        }


    }
}
