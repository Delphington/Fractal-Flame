package backend.academy.transformation;

import backend.academy.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = Math.atan(point.x() / point.y());
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point(r * Math.sin(theta * r), -r * Math.cos(theta * r));
    }
}
