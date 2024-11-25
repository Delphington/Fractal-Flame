package backend.academy;

import backend.academy.render.Rect;
import lombok.experimental.UtilityClass;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class SrcRandom {
    private static Random random = new Random();


    //[start, finish)
    public int getRandomInt(double start, double finish) {
        return (int) (random.nextDouble(finish - start) + start);
    }


    public double getRandomDouble(double start, double finish) {
        return random.nextDouble(finish - start ) + start;
    }


    public Point getRandomPoint(double x1, double x2, double y1, double y2){
        return new Point(getRandomDouble(x1,x2), getRandomDouble(y1,y2));
    }

    public static Point random(Rect rect) {
        return new Point(
            ThreadLocalRandom.current().nextDouble(rect.x(), rect.x() + rect.width()),
            ThreadLocalRandom.current().nextDouble(rect.y(), rect.y() + rect.height())
        );
    }

}
