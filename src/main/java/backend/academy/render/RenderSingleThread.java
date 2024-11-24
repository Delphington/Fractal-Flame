package backend.academy.render;

import backend.academy.FractalImage;
import backend.academy.Space;
import backend.academy.transformation.Transformation;
import java.util.List;

public class RenderSingleThread implements Renderer {

    public void rend(
        FractalImage fractalImage,
        List<Space> affineSpaces,
        List<Transformation> transformationFunctions,
        int numberSamples,
        int iterationPerSample,
        int symmetry) {

        for (int i = 0; i < numberSamples; i++) {
            transformPoint(fractalImage, new SrvRandomEnvironment(affineSpaces, transformationFunctions),
                symmetry, iterationPerSample);
        }
    }
}
