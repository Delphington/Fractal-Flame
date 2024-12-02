package backend.academy.render;

import backend.academy.model.FractalImage;
import backend.academy.model.Pixel;
import backend.academy.model.Point;
import backend.academy.model.Space;
import backend.academy.SrcRandom;
import backend.academy.transformation.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface Render {

    void rend(
        FractalImage fractalImage, List<Space> spaces, Transformation transformation,
        int numberSamples, int iterationSample, int symmetry, Rect rect
    );

    default void transformPoint(
        FractalImage fractalImage,
        SrvRandomEnvironment srvRandomEnvironment,
        int symmetry,
        int iterationSample, Rect rect
    ) {

        Point point = SrcRandom.random(rect);

        for (int step = -20; step < iterationSample; step++) {
            point = srvRandomEnvironment.generateResource(point);
            if (step > 0) {
                setSymmetryTransformation(fractalImage, srvRandomEnvironment.randomPoint(), symmetry, rect);
            }
        }

    }

    // Метод  трансформаций
    private void setSymmetryTransformation(
        FractalImage fractalImage, Point point, int symmetry, Rect rect
    ) {
        //Угол поворота
        double theta = 0.0;
        for (int s = 0; s < symmetry; s++) {
            theta += 2 * Math.PI / symmetry;

            Point termPoint = getRotate(rect, point, theta);

            if (!rect.contains(termPoint)) {
                continue;
            }

            Optional<Pixel> optional = getMapPixel(fractalImage, termPoint, rect);

            optional.ifPresent(pixel -> {
                synchronized (pixel) {
                    changePixel(pixel, Space.color());
                }
            });

        }

    }

    private void changePixel(Pixel pixel, Color color) {
        if (pixel.cnt() == 0) {
            pixel.cnt(1);
            pixel.setColor(color.getRed(), color.getGreen(), color.getBlue());
        } else {
            pixel.cnt(pixel.cnt() + 1);
            pixel.setCorrect(color.getRed(), color.getGreen(), color.getBlue());
        }
    }

    private double getCenterCoordinate(double val1, double val2) {
        return val1 + val2 / 2;
    }

    private Point getRotate(Rect rect, Point point, double theta) {
        double centerCorX = getCenterCoordinate(rect.x(), rect.width());
        double centerCorY = getCenterCoordinate(rect.y(), rect.height());

        double rotateCorX = (point.x() - centerCorX) * Math.cos(theta) - (point.y() - centerCorY) * Math.sin(theta);
        double rotateCorY = (point.x() - centerCorX) * Math.sin(theta) + (point.y() - centerCorY) * Math.cos(theta);
        return new Point(rotateCorX + centerCorX, rotateCorY + centerCorY);
    }

    private Optional<Pixel> getMapPixel(FractalImage fractalImage, Point point, Rect world) {
        if (!world.contains(point)) {
            return Optional.ofNullable(null);
        }

        Pixel p = fractalImage.pixel(
            (int) ((point.x() - world.x()) / world.width() * fractalImage.width()),
            (int) ((point.y() - world.y()) / world.height() * fractalImage.height())
        );
        return Optional.ofNullable(p);
    }
}
