package backend.academy.transformation;

import backend.academy.model.Point;
import java.util.function.Function;

// функция-преобразование
public interface Transformation extends Function<Point, Point> {
}

