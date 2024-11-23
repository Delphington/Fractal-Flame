package backend.academy;

import java.util.Random;

public class Space {
    /**
     * x(n+1)     (a, b)  (xn)     (c)
     * =                =
     * y(n+1)     (d, e)  (yn)     (f)
     */

    private final int MAX_COLOR = 256;
    private final double MIN_NUMBER = -1.5;
    private final double MAX_NUMBER = 1.5;

    private int red;
    private int green;
    private int blue;

    private double a = 0.1;
    private double b;
    private double d = 0.1;
    private double e;

    private double c;
    private double f;

    Space() {
        generateRandom();
    }

    private boolean checkConditional() {
        return
            (Math.pow(a, 2) + Math.pow(d, 2) < 1)
                && (Math.pow(b, 2) + Math.pow(e, 2) < 1)
                && (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2)
                < 1 + Math.pow((a * e - b * d), 2));
    }

    @Override public String toString() {
        return "Space{" +
            "MAX_COLOR=" + MAX_COLOR +
            ", MIN_NUMBER=" + MIN_NUMBER +
            ", MAX_NUMBER=" + MAX_NUMBER +
            ", red=" + red +
            ", green=" + green +
            ", blue=" + blue +
            ", a=" + a +
            ", b=" + b +
            ", d=" + d +
            ", e=" + e +
            ", c=" + c +
            ", f=" + f +
            '}';
    }

    private void generateRandom() {
        red = (int) (Math.random() * MAX_COLOR);
        green = (int) (Math.random() * MAX_COLOR);
        blue = (int) (Math.random() * MAX_COLOR);
        Random random = new Random();

        // Генерация случайного числа в диапазоне от -1.5 до 1.5
        double min = -1.5;
        double max = 1.5;

        do {
            a = min + (max - min) * random.nextDouble();
            b = min + (max - min) * random.nextDouble();

            d = min + (max - min) * random.nextDouble();

            e = min + (max - min) * random.nextDouble();

            c = min + (max - min) * random.nextDouble();
            f = min + (max - min) * random.nextDouble();

        } while (!checkConditional());
    }

    public static void main(String[] args) {
        Space space = new Space();
        System.out.println(space);

    }

}
