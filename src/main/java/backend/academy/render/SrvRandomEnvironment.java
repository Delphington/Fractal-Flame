package backend.academy.render;

import backend.academy.Constance;
import backend.academy.Point;
import backend.academy.Space;
import backend.academy.SrcRandom;
import backend.academy.transformation.Transformation;
import lombok.Getter;
import java.util.List;

class SrvRandomEnvironment implements Constance {
    private List<Space> spaces;
    private List<Transformation> transformations;

    @Getter
    private Space randomSpace;
    @Getter
    private Point randomPoint;

    public SrvRandomEnvironment(List<Space> spaces, List<Transformation> transformations) {
        this.spaces = spaces;
        this.transformations = transformations;
    }

    public void generateResource(){
        Space termSpace = spaces.get(SrcRandom.getRandomInt(0, spaces.size()));
        Transformation transformationFunction = transformations.get(SrcRandom.getRandomInt(0, transformations.size()));
        Point point = termSpace.apply(SrcRandom.getRandomPoint(X_MIN, X_MAX, Y_MIN, Y_MAX));

        randomPoint = transformationFunction.apply(point); //преобразование
        randomSpace  = termSpace;
    }
}
