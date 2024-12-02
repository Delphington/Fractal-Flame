package backend.academy;

import backend.academy.model.Point;
import backend.academy.render.Rect;
import java.util.concurrent.ThreadLocalRandom;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@UtilityClass
@Log4j2
public class SrcRandom {

    //[start, finish)
    public static int getRandomInt(double start, double finish) {
        return (int) (ThreadLocalRandom.current().nextDouble(finish - start) + start);
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static Point random(Rect rect) {
        if (rect != null) {
            return new Point(
                ThreadLocalRandom.current().nextDouble(rect.x(), rect.x() + rect.width()),
                ThreadLocalRandom.current().nextDouble(rect.y(), rect.y() + rect.height())
            );
        }
        log.warn("Не корретная передача rect в SrvRandom.random");
        throw new NullPointerException("Не корретная передача rect в SrvRandom.random");
    }
}
