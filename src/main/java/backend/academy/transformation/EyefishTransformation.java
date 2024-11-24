package backend.academy.transformation;

import backend.academy.Point;

public class EyefishTransformation implements Transformation {
    private double r;

    private double changeCoordinate(double value) {
        return value * (2 / (r + 1));
    }

    @Override
    public Point apply(Point point) {
        r = Math.sqrt(Math.pow(point.x(),2) + Math.pow(point.y(), 2));
        return new Point(changeCoordinate(point.x()), changeCoordinate(point.y()));
    }
}
