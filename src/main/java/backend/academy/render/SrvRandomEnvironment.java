package backend.academy.render;

import backend.academy.Point;
import backend.academy.Space;
import backend.academy.SrcRandom;
import backend.academy.transformation.Transformation;
import java.util.List;
import lombok.Getter;

class SrvRandomEnvironment {
    private List<Space> spaces;
    private Transformation transformation;

    @Getter
    private Space randomSpace;
    @Getter
    private Point randomPoint;

    SrvRandomEnvironment(List<Space> spaces, Transformation transformation) {
        this.spaces = spaces;
        this.transformation = transformation;
    }

    Point generateResource(Point point) {
        //Рандомное аффиное пространство
        Space termSpace = spaces.get(SrcRandom.getRandomInt(0, spaces.size()));
        //тут создавал transformation и к нему применял

        // Применяем преобразование к точке
        Point transformedPoint = termSpace.apply(point);
        transformedPoint = transformation.apply(transformedPoint); // преобразование

        // Сохраняем случайное пространство и точку
        randomSpace = termSpace;
        randomPoint = transformedPoint;

        return transformedPoint;
    }
}
