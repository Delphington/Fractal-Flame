package backend.academy.input;

import backend.academy.Space;
import backend.academy.transformation.DiamondTransformation;
import backend.academy.transformation.DiscTransformation;
import backend.academy.transformation.EyefishTransformation;
import backend.academy.transformation.HandkerchiefTransformation;
import backend.academy.transformation.HorseshoeTransformation;
import backend.academy.transformation.HyperbolicTransformation;
import backend.academy.transformation.LinearTransformation;
import backend.academy.transformation.PolarTransformation;
import backend.academy.transformation.SinTransformation;
import backend.academy.transformation.SphericalTransformation;
import backend.academy.transformation.SpiralTransformation;
import backend.academy.transformation.Transformation;
import lombok.Getter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputSimpleParam {
    private PrintStream printStream = System.out;
    private Scanner scan = new Scanner(System.in);

    public InputSimpleParam() {
        //Заполнение аффинными пространственнами
        spaces = new ArrayList<>();
        spaces.add(new Space());
        spaces.add(new Space());
        spaces.add(new Space());
        spaces.add(new Space());
        spaces.add(new Space());
        spaces.add(new Space());

        //---------------------
        transformations = new ArrayList<>();
        transformations.add(new DiamondTransformation());
        transformations.add(new DiscTransformation());
        transformations.add(new EyefishTransformation());
        transformations.add(new HandkerchiefTransformation());
        transformations.add(new HorseshoeTransformation());
        transformations.add(new HyperbolicTransformation());
        transformations.add(new LinearTransformation());
        transformations.add(new PolarTransformation());
        transformations.add(new SinTransformation());
        transformations.add(new SphericalTransformation());
        transformations.add(new SpiralTransformation());
    }

    @Getter
    private List<Space> spaces;
    @Getter
    List<Transformation> transformations;

    @Getter private int height = 1080;
    @Getter private int weight = 1920;
    @Getter private int sampleCount = 2000;
    @Getter private int iterationCount = 2000;
    @Getter private int symmetryCount = 20;
    @Getter private int threadCount = 6;
    @Getter private static int numberTransformation;

    private boolean isValid(Integer x) {
        return (x > 0) && x < 10000;
    }

    private int inputParseString(String inputText, String input) {
        while (true) {
            try {
                int num = Integer.parseInt(input);
                if (isValid(num)) {
                    return num;
                }
                printStream.println("не верный размер");
            } catch (RuntimeException exception) {
                printStream.println("Введено не число, попробуйте еще раз! ");
            }
            printStream.print(inputText);
        }
    }

    private int inputValue(String inputText, Integer num) {
        printStream.print(inputText);
        String input = scan.nextLine();
        if (!input.isEmpty()) {
            return inputParseString(inputText, input);
        }
        return num;
    }

    public void input() {
        height = inputValue("Введите высоту картинки: ", height);
        weight = inputValue("Введите ширину картинки: ", weight);
        sampleCount = inputValue("Введите количество выборок для рендеринга: ", sampleCount);
        iterationCount = inputValue("Введите количество итераций для выборки: ", iterationCount);
        symmetryCount = inputValue("Введите количество симметрий: ", symmetryCount);
        threadCount = inputValue("Введите количество потоков: ", threadCount);
    }
}
