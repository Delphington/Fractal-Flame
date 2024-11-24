package backend.academy;

import lombok.experimental.UtilityClass;
import java.util.Random;

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


}
