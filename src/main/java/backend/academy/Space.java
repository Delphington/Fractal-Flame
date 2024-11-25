package backend.academy;

import lombok.Getter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;
import java.awt.Color;
import java.util.Random;
//import static backend.academy.render.HabrRendererSingleThreaded.randomDouble;

//todo: запросить количество аффиных пространтсв


@Getter
@ToString
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

    private double a;
    private double b;
    private double d;
    private double e;

    private double c;
    private double f;
    @Getter
    static private Color color;
    // Константы для генерации случайных цветов и ограничений для значений
    private static final Integer COUNT_OF_COLORS = 256; // Количество возможных цветов (0-255)
    private static final Integer MIN_VALUE = -1; // Минимальное значение для случайных чисел
    private static final Integer MAX_VALUE = 1; // Максимальное значение для случайных чисел

    public Space() {
        generateRandom();
    }

    private boolean checkConditional() {
        return
            (Math.pow(a, 2) + Math.pow(d, 2) < 1)
                && (Math.pow(b, 2) + Math.pow(e, 2) < 1)
                && (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2)
                < 1 + Math.pow((a * e - b * d), 2));
    }

    private void generateRandom() {
        red = (int) (Math.random() * MAX_COLOR);
        green = (int) (Math.random() * MAX_COLOR);
        blue = (int) (Math.random() * MAX_COLOR);
        Random random = new Random();
        color = new Color(red, green,blue);

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

    // Метод для применения аффинного преобразования к точке
    public Point apply(Point point) {
        return new Point(a * point.x() + b * point.y() + c, d * point.x() + e * point.y() + f);
    }


}
