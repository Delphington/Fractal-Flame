package backend.academy.transformation;

import backend.academy.model.Point;

public class SpiralTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = Math.atan(point.x() / point.y());
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point((1 / r) * (Math.cos(theta) + Math.sin(r)), (1 / r) * (Math.sin(theta) + Math.cos(r)));
    }
}
