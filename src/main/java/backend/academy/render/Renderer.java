package backend.academy.render;

import backend.academy.Constance;
import backend.academy.FractalImage;
import backend.academy.Pixel;
import backend.academy.Point;
import backend.academy.Space;
import backend.academy.transformation.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer extends Constance {

    void rend(
        FractalImage fractalImage, List<Space> spaces, List<Transformation> transformation,
        int numberSamples, int iterationSample, int symmetry
    );

    default void transformPoint(FractalImage fractalImage, SrvRandomEnvironment r, int symmetry, int iterationSample) {
        for (int step = -20; step < iterationSample; step++) {
            r.generateResource();
            setSymmetryTransformation(fractalImage, r.randomSpace(), r.randomPoint(), symmetry);
        }
    }

    // Метод  трансформаций
    private void setSymmetryTransformation(FractalImage fractalImage, Space affineSpace, Point point, int symmetry) {
        double theta = 0; // Угол поворота

        for (int s = 0; s < symmetry; s++) {
            theta += 2 * Math.PI / symmetry;

            // Получаем симметричную точку
            Point symmentryPoint = getSymmetryPoint(point, theta);

            //  точка в пределах заданной конфигурации
            if ( (symmentryPoint.x() >= X_MIN && symmentryPoint.x() <= X_MAX)
                && (symmentryPoint.y() >= Y_MIN && symmentryPoint.y() <= Y_MAX)) {

                // результирующую точка
                Point resultPoint = getResultPoint(symmentryPoint, fractalImage.width(), fractalImage.height());

                //Если в картинки
                if (fractalImage.isInImage(resultPoint)) {
                    synchronized (fractalImage.pixel(resultPoint)) {
                        changeColor(fractalImage.pixel(resultPoint), affineSpace);
                    }
                }
            }
        }
    }

    // x' = x ⋅ cos(θ) - y ⋅ sin(θ)
    //y' = x ⋅ sin(θ) + y ⋅ cos(θ)
    private Point getSymmetryPoint(Point point, double theta) {
        return new Point(point.x() * Math.cos(theta) - point.y() * Math.sin(theta),
            point.x() * Math.sin(theta) + point.y() * Math.cos(theta)
        );
    }

    // Метод для получения результирующей точки в фрактальном изображении
    private Point getResultPoint(
        Point rotPoint, double xRes, double yRes) {
        return new Point(xRes - (int) (((X_MAX - rotPoint.x()) / (X_MAX - X_MIN)) * xRes),
            yRes - (int) (((Y_MAX - rotPoint.y()) / (Y_MAX - Y_MIN)) * yRes)
        );
    }

    // Смена цвет на основе пространства
    private void changeColor(Pixel pixel, Space affineSpace) {
        pixel.r((pixel.r() + affineSpace.red()) / 2);
        pixel.g((pixel.g() + affineSpace.green()) / 2);
        pixel.b((pixel.b() + affineSpace.blue()) / 2);
        pixel.incrementHitCount();
    }
}
