package backend.academy.render;

import backend.academy.Point;
import backend.academy.Space;
import backend.academy.SrcRandom;
import backend.academy.transformation.Transformation;
import lombok.Getter;
import java.util.List;

class SrvRandomEnvironment {
    private List<Space> spaces;
    private Transformation transformations;

    @Getter
    private Space randomSpace;
    @Getter
    private Point randomPoint;

    public SrvRandomEnvironment(List<Space> spaces, Transformation transformations) {
        this.spaces = spaces;
        this.transformations = transformations;
    }

    public Point generateResource(Point point) {
        //Рандомное аффиное пространство
        Space termSpace = spaces.get(SrcRandom.getRandomInt(0, spaces.size()));

        //Рандомноая трансформация
//        Transformation transformationFunction = transformations.get(SrcRandom.getRandomInt(0, transformations.size()));
        Transformation transformationFunction = transformations;

        point = termSpace.apply(point);

        point = transformationFunction.apply(point); //преобразование
        randomSpace = termSpace;
        randomPoint = point;
        return  point;
    }
}
