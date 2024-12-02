package backend.academy.render;

import backend.academy.SrcRandom;
import backend.academy.model.Point;
import backend.academy.model.Space;
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

        // Применяем преобразование к точке
        Point transformedPoint = termSpace.apply(point);
        transformedPoint = transformation.apply(transformedPoint); // преобразование

        randomSpace = termSpace;
        randomPoint = transformedPoint;

        return transformedPoint;
    }
}
