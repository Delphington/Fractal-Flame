package backend.academy.transformation;

import backend.academy.Point;

public class HorseshoeTransformation implements Transformation{
    @Override
    public Point apply(Point point) {
        double theta = Math.atan(point.x() / point.y());
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return  new Point((1/r)*(point.x() - point.y())*(point.x() + point.y()), (1/r)*2*point.x()*point.y());
    }
}
