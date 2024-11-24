package backend.academy.transformation;

import backend.academy.Point;

public class SphericalTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point((1/Math.pow(r,2))*point.x(), (1/Math.pow(r,2))* point.y());
    }
}
