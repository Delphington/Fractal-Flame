package backend.academy;

import backend.academy.render.Rect;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SrcRandom {
    private static Random random = new Random();

    //[start, finish)
    public int getRandomInt(double start, double finish) {
        return (int) (random.nextDouble(finish - start) + start);
    }

    public static Point random(Rect rect) {
        return new Point(
            ThreadLocalRandom.current().nextDouble(rect.x(), rect.x() + rect.width()),
            ThreadLocalRandom.current().nextDouble(rect.y(), rect.y() + rect.height())
        );
    }

}
