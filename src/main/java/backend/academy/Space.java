package backend.academy;

import java.awt.Color;
import java.util.Random;
import lombok.Getter;
//todo: запросить количество аффиных пространтсв

public class Space {
    /**
     * x(n+1)     (a, b)  (xn)     (c)
     * =                =
     * y(n+1)     (d, e)  (yn)     (f)
     */
    private final static int MAX_COLOR = 256;
    private final static double MIN_NUMBER = -1.5;
    private final static double MAX_NUMBER = 1.5;

    private int red;
    private int green;
    private int blue;
    private double a;
    private double b;
    private double d;
    private double e;
    private double c;
    private double f;

    @Getter
    private static Color color;

    public Space() {
        Random random = new Random();
        red = random.nextInt(MAX_COLOR);
        green = random.nextInt(MAX_COLOR);
        blue = random.nextInt(MAX_COLOR);
        color = new Color(red, green, blue);

        do {
            do {
                a = MIN_NUMBER + (MAX_NUMBER - MIN_NUMBER) * random.nextDouble();
                d = MIN_NUMBER + (MAX_NUMBER - MIN_NUMBER) * random.nextDouble();
            } while (Math.pow(a, 2) + Math.pow(d, 2) < 1);

            do {
                b = MIN_NUMBER + (MAX_NUMBER - MIN_NUMBER) * random.nextDouble();
                e = MIN_NUMBER + (MAX_NUMBER - MIN_NUMBER) * random.nextDouble();
            } while (Math.pow(b, 2) + Math.pow(e, 2) < 1);

            c = MIN_NUMBER + (MAX_NUMBER - MIN_NUMBER) * random.nextDouble();
            f = MIN_NUMBER + (MAX_NUMBER - MIN_NUMBER) * random.nextDouble();

        } while (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2)
            < 1 + Math.pow((a * e - b * d), 2));
    }

    // Применения аффинного преобразования к точке
    public Point apply(Point point) {
        return new Point(a * point.x() + b * point.y() + c, d * point.x() + e * point.y() + f);
    }
}
