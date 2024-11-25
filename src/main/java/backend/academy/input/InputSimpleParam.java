package backend.academy.input;

import backend.academy.Constance;
import backend.academy.Space;
import backend.academy.config.ImageFormat;
import backend.academy.config.ImageUtils;
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
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.Getter;

//todo: не все работают

public class InputSimpleParam implements Constance {
    private final PrintStream printStream = System.out;
    private final Scanner scan = new Scanner(System.in);
    private int count = 1;

    @Getter private Map<String, Transformation> variousTransformation;
    @Getter private List<Space> spaces;
    //Дефолтное заполение переменных
    @Getter private String typeTransformation = "spiral";
    @Getter private int height = 1080;
    @Getter private int weight = 1920;
    @Getter private int sampleCount = 2000;
    @Getter private int iterationCount = 1000;
    @Getter private int spaceCount = 40;
    @Getter private int symmetryCount = 4;
    @Getter private int threadCount = 6;
    @Getter private ImageFormat imageFormat = ImageFormat.PNG;

    public InputSimpleParam() {
        spaces = new ArrayList<>();
        variousTransformation = new HashMap<>();
        variousTransformation.put("eyefish", new EyefishTransformation());
        variousTransformation.put("diamond", new DiamondTransformation());
        variousTransformation.put("disc", new DiscTransformation());
        variousTransformation.put("handkerchief", new HandkerchiefTransformation());
        variousTransformation.put("horseshoe", new HorseshoeTransformation());
        variousTransformation.put("hyperbolic", new HyperbolicTransformation());
        variousTransformation.put("linear", new LinearTransformation());
        variousTransformation.put("polar", new PolarTransformation());
        variousTransformation.put("sin", new SinTransformation());
        variousTransformation.put("spherical", new SphericalTransformation());
        variousTransformation.put("spiral", new SpiralTransformation());
    }

    private void printTransformation() {
        printStream.println("Виды трансформации: ");
        int counterTransformation = 0;
        for (Map.Entry<String, Transformation> item : variousTransformation.entrySet()) {
            counterTransformation++;
            printStream.println("["+ counterTransformation + "] " + item.getKey());
        }
    }

    private void initializationAffineSpace() {
        for (int i = 0; i < spaceCount; i++) {
            spaces.add(new Space());
        }
    }

    private boolean isValid(Integer x, Integer limitTop) {
        return (x > 0) && x < limitTop;
    }

    private int inputParam(String inputText, Integer num,  Integer limitTop) {
        printStream.print(inputText);
        while (true) {
            String input = scan.nextLine();
            try {
                if (input.isEmpty()) {
                    return num;
                }
                int termNum = Integer.parseInt(input);
                if (isValid(termNum,limitTop)) {
                    return termNum;
                }
                printStream.println("не верный размер");
            } catch (RuntimeException exception) {
                printStream.println("Введено не число, попробуйте еще раз!");
            }
            printStream.print(inputText);
        }
    }


    private String inputTypeTransformation(String textInput) {
        printTransformation();
        printStream.print(textInput);

        while (true) {
            String input = scan.nextLine();
            if (input.isEmpty()) {
                return typeTransformation;
            }
            String termInput = input.trim().toLowerCase();
            if (variousTransformation.get(termInput) != null) {
                return termInput;
            } else {
                printStream.println("Ввод не корректный! Попробуйте еще раз!");
                printStream.print(textInput);
            }
        }
    }

    private ImageFormat inputTypeImage(String text){
        ImageFormat termImageFormat;
        while(true){
            printStream.print(text);
            String input = scan.nextLine();
            if (input.isEmpty()){
                return imageFormat;
            }
            try{
                termImageFormat = ImageFormat.valueOf(input.toUpperCase());
                return termImageFormat;
            }catch (IllegalArgumentException exception){
                printStream.println("Невернный ввод, попробуйте еще раз!");
            }
        }
    }


    public void input() {
        printStream.println("При нажатии Enter - конфигурации устанавливается дефолтное значение!");

        height = inputParam(String.format("[%d] Введите высоту картинки: ", count++), height, MAX_SIZE);
        weight = inputParam(String.format("[%d] Введите ширину картинки: ", count++), weight, MAX_SIZE);
        sampleCount = inputParam(String.format("[%d] Введите количество выборок для рендеринга: ", count++), sampleCount,MAX_CONFIG);
        iterationCount = inputParam(String.format("[%d] Введите количество итераций для выборки: ", count++), iterationCount, MAX_CONFIG);
        spaceCount = inputParam(String.format("[%d] Введите количество аффинных пространств: ", count++), spaceCount,MAX_CONFIG);
        initializationAffineSpace();

        symmetryCount = inputParam(String.format("[%d] Введите количество симметрий: ", count++), symmetryCount,MAX_SYMMETRY_THREAD);
        threadCount = inputParam(String.format("[%d] Введите количество потоков: ", count++), threadCount,MAX_SYMMETRY_THREAD);
        imageFormat = inputTypeImage(String.format("[%d] Введите тип вывода картинки - {jpeg, bmp, png}: ", count++));
        printStream.println("----------------------------------");
        typeTransformation = inputTypeTransformation("Введите вид трансформации: ");
    }

    public void printInputConfig(){
        count =1;
        printStream.println("========================================================");
        printStream.println("Параметры настроек:");
        printStream.printf("[%d] Размер изображения: %dx%d%n", count++, height, weight);
        printStream.printf("[%d] Выборок для рендинга: %d%n", count++, sampleCount);
        printStream.printf("[%d] Количество итераций для выборки: %d%n", count++, iterationCount);
        printStream.printf("[%d] Количество аффинных пространств: %d%n", count++, spaceCount);
        printStream.printf("[%d] Количество симметрий: %d%n", count++, symmetryCount);
        printStream.printf("[%d] Количество потоков: %d%n", count++, threadCount);
        printStream.printf("[%d] Вид трансформации: %s%n", count++, typeTransformation);
        printStream.printf("[%d] Формат фото: %s%n", count++, imageFormat);
    }
}
