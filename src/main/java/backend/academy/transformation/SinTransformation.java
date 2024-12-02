package backend.academy.transformation;

import backend.academy.model.Point;

public class SinTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
