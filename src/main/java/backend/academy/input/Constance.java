package backend.academy.input;

import backend.academy.output.ImageFormat;
import backend.academy.transformation.HyperbolicTransformation;
import backend.academy.transformation.Transformation;

public interface Constance {

    //Условные ограниче по максимальности значений ввода
    int MAX_SIZE = 3000;
    int MAX_CONFIG = 100000;
    int MAX_SYMMETRY_THREAD = 1000;

    Transformation DEFAULT_TYPE_TRANSFORMATION = new HyperbolicTransformation();
    int DEFAULT_HEIGHT = 1080;
    int DEFAULT_WEIGHT = 1920;

    int DEFAULT_SAMPLE_COUNT = 2000;

    int DEFAULT_ITERATION_COUNT = 1000;
    int DEFAULT_SPACE_COUNT = 60;
    int DEFAULT_SYMMETRY_COUNT = 5;
    int DEFAULT_THREAD_COUNT = 5;
    ImageFormat DEFAULT_IMAGE_FORMAT = ImageFormat.PNG;
}
