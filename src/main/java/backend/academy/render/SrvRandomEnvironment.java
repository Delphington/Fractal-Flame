package backend.academy.render;

import backend.academy.Point;
import backend.academy.Space;
import backend.academy.SrcRandom;
import backend.academy.transformation.Transformation;
import lombok.Getter;
import java.util.List;

class SrvRandomEnvironment {
    private List<Space> spaces;
    private Transformation transformation;

    @Getter
    private Space randomSpace;
    @Getter
    private Point randomPoint;

    public SrvRandomEnvironment(List<Space> spaces, Transformation transformation) {
        this.spaces = spaces;
        this.transformation = transformation;
    }

    public Point generateResource(Point point) {
        //Рандомное аффиное пространство
        Space termSpace = spaces.get(SrcRandom.getRandomInt(0, spaces.size()));


        //тут создавал transpoint и к нему применял
        point = termSpace.apply(point);

        point = transformation.apply(point); //преобразование
        randomSpace = termSpace;
        randomPoint = point;
        return  point;
    }
}
