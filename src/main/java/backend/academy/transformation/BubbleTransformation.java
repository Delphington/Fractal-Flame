package backend.academy.transformation;

import backend.academy.model.Point;

@SuppressWarnings("MagicNumber")
public class BubbleTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double kf = 4 / (Math.pow(r, 2) + 4);
        return new Point(point.x() * kf, point.y() * kf);
    }
}
