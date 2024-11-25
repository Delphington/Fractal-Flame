package backend.academy.transformation;

import backend.academy.Point;

public class DiscTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = Math.atan(point.x() / point.y());
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point(theta * Math.sin(Math.PI * r) / Math.PI, theta * Math.cos(Math.PI * r) / Math.PI);
    }
}
