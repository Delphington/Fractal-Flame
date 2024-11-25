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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputSimpleParam {
    private PrintStream printStream = System.out;
    private Scanner scan = new Scanner(System.in);
    private int count = 1;
    private static int counterTransformation = 0;

    //Todo; ввод типа трансформации

    public InputSimpleParam() {
        //Заполнение аффинными пространственнами
        spaces = new ArrayList<>();
        variousTransformation = new HashMap<>();
        initializationTransformation();
    }

    private void createAffineSpace() {
        for (int i = 0; i < spaceCount; i++) {
            spaces.add(new Space());
        }
    }

    private void initializationTransformation() {
        variousTransformation.put("Eyefish", new EyefishTransformation());
        variousTransformation.put("Diamond", new DiamondTransformation());
        variousTransformation.put("Disc", new DiscTransformation());
        variousTransformation.put("Handkerchief", new HandkerchiefTransformation());
        variousTransformation.put("Horseshoe", new HorseshoeTransformation());
        variousTransformation.put("Hyperbolic", new HyperbolicTransformation());
        variousTransformation.put("Linear", new LinearTransformation());
        variousTransformation.put("Polar", new PolarTransformation());
        variousTransformation.put("Sin", new SinTransformation());
        variousTransformation.put("Spherical", new SphericalTransformation());
        variousTransformation.put("Spiral", new SpiralTransformation());
    }

    private void printTransformation() {
        printStream.println("Виды трансформации: ");
        int counterTransformation = 0;
        for (Map.Entry<String, Transformation> item : variousTransformation.entrySet()) {
            counterTransformation++;
            printStream.println(counterTransformation + ") " + item.getKey());
        }
    }

    @Getter
    private Map<String, Transformation> variousTransformation;

    @Getter
    private List<Space> spaces;

    @Getter private int height = 1080;
    @Getter private int weight = 1920;
    @Getter private int sampleCount = 2000;
    @Getter private int iterationCount = 1000;

    @Getter private int spaceCount = 40;

    @Getter private int symmetryCount = 4;
    @Getter private int threadCount = 6;

    @Getter private static int numberTransformation;

    private boolean isValid(Integer x) {
        return (x > 0) && x < 10000;
    }

    private int inputParseString(String inputText, String input) {
        while (true) {
            try {
                //todo: закиливание если слишком много ввели
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

    //todo: ограничение на тип транспозиции
    public void input() {
        height = inputValue("[" + count++ + "] Введите высоту картинки: ", height);
        weight = inputValue("[" + count++ + "] Введите ширину картинки: ", weight);
        sampleCount = inputValue("[" + count++ + "] Введите количество выборок для рендеринга: ", sampleCount);
        iterationCount = inputValue("[" + count++ + "] Введите количество итераций для выборки: ", iterationCount);
        spaceCount = inputValue("[" + count++ + "] Введите количество аффинных пространств: ", spaceCount);
        createAffineSpace();
        symmetryCount = inputValue("[" + count++ + "] Введите количество симметрий: ", symmetryCount);
        threadCount = inputValue("[" + count++ + "] Введите количество потоков: ", threadCount);
        printStream.println("----------------------------------");

        printTransformation();
        printStream.print("Введите вид трансформации: ");


    }
}
