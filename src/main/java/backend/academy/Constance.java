package backend.academy;

import backend.academy.config.ImageFormat;

public interface Constance {


    //Условные ограниче по максимальности значений ввода
    int MAX_SIZE = 3000;
    int MAX_CONFIG = 100000;
    int MAX_SYMMETRY_THREAD = 1000;

    String DEFAULT_TYPE_TRANSFORMATION = "spiral";
    int DEFAULT_HEIGHT = 1080;
    int DEFAULT_WEIGHT = 1920;

    int DEFAULT_SAMPLE_COUNT = 4000;

    int DEFAULT_ITERATION_COUNT = 2000;
    int DEFAULT_SPACE_COUNT = 40;
    int DEFAULT_SYMMETRY_COUNT = 5;
    int DEFAULT_THREAD_COUNT = 10;
    ImageFormat DEFAULT_IMAGE_FORMAT = ImageFormat.PNG;


}
