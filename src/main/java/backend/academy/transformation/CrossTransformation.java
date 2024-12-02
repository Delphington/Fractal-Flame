package backend.academy.transformation;

import backend.academy.model.Point;

public class CrossTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double value = Math.sqrt(1 / Math.pow(Math.pow(point.x(), 2) + Math.pow(point.y(), 2), 2));
        return new Point(point.x() * value, point.y() * value);
    }
}
