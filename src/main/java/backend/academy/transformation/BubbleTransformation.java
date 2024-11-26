package backend.academy.transformation;

import backend.academy.Point;

public class BubbleTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point(point.x() * (4 / (Math.pow(r, 2) + 4)), point.y() * (4 / (Math.pow(r, 2) + 4)));
    }
}
