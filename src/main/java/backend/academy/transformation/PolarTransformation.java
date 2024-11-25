package backend.academy.transformation;

import backend.academy.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = Math.atan(point.x() / point.y());
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point(theta / Math.PI, r - 1);
    }
}
