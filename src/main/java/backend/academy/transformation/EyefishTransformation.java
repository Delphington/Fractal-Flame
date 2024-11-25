package backend.academy.transformation;

import backend.academy.Point;

public class EyefishTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point((2 / (r + 1)) * point.x(), (2 / (r + 1)) * point.y());
    }
}
