package backend.academy.input;

import backend.academy.model.Space;
import backend.academy.output.ImageFormat;
import backend.academy.transformation.BubbleTransformation;
import backend.academy.transformation.CrossTransformation;
import backend.academy.transformation.DiamondTransformation;
import backend.academy.transformation.DiscTransformation;
import backend.academy.transformation.HandkerchiefTransformation;
import backend.academy.transformation.HyperbolicTransformation;
import backend.academy.transformation.PolarTransformation;
import backend.academy.transformation.SinTransformation;
import backend.academy.transformation.SphericalTransformation;
import backend.academy.transformation.SpiralTransformation;
import backend.academy.transformation.TangentTransformation;
import backend.academy.transformation.Transformation;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InputSimpleParam implements Constance {
    private final PrintStream printStream = System.out;
    private final Scanner scan = new Scanner(System.in);
    private int count = 1;

    @Getter @Setter
    private static InputSimpleParam inputSrv;

    @Getter
    private Map<String, Transformation> variousTransformation;
    @Getter
    private List<Space> spaces;
    @Getter
    private String typeTransformation;
    @Getter
    private int height;
    @Getter
    private int weight;
    @Getter
    private int sampleCount;
    @Getter
    private int iterationCount;
    @Getter
    private int spaceCount;
    @Getter
    private int symmetryCount;
    @Getter @Setter
    private int threadCount;
    @Getter
    private ImageFormat imageFormat;

    public InputSimpleParam() {
        spaces = new ArrayList<>();
        variousTransformation = new HashMap<>();
        variousTransformation.put("diamond", new DiamondTransformation());
        variousTransformation.put("disc", new DiscTransformation());
        variousTransformation.put("handkerchief", new HandkerchiefTransformation());
        variousTransformation.put("hyperbolic", new HyperbolicTransformation());
        variousTransformation.put("polar", new PolarTransformation());
        variousTransformation.put("sin", new SinTransformation());
        variousTransformation.put("spherical", new SphericalTransformation());
        variousTransformation.put("spiral", new SpiralTransformation());
        variousTransformation.put("cross", new CrossTransformation());
        variousTransformation.put("tangent", new TangentTransformation());
        variousTransformation.put("bubble", new BubbleTransformation());

        typeTransformation = DEFAULT_TYPE_TRANSFORMATION;
        height = DEFAULT_HEIGHT;
        weight = DEFAULT_WEIGHT;
        sampleCount = DEFAULT_SAMPLE_COUNT;
        iterationCount = DEFAULT_ITERATION_COUNT;
        spaceCount = DEFAULT_SPACE_COUNT;
        symmetryCount = DEFAULT_SYMMETRY_COUNT;
        threadCount = DEFAULT_THREAD_COUNT;
        imageFormat = DEFAULT_IMAGE_FORMAT;
        initializationAffineSpace();

    }

    private void printTransformation() {
        printStream.println("Виды трансформации: ");
        int counterTransformation = 0;
        for (Map.Entry<String, Transformation> item : variousTransformation.entrySet()) {
            counterTransformation++;
            printStream.println("[" + counterTransformation + "] " + item.getKey());
        }
    }

    private void initializationAffineSpace() {
        spaces.clear();
        for (int i = 0; i < spaceCount; i++) {
            spaces.add(new Space());
        }
    }

    private boolean isValid(Integer x, Integer limitTop) {
        return (x > 0) && x < limitTop;
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
            if (variousTransformation.get(termInput.toLowerCase()) != null) {
                return termInput;
            } else {
                printStream.println("Ввод не корректный! Попробуйте еще раз!");
                printStream.print(textInput);
            }
        }
    }

    private ImageFormat inputTypeImage(String text) {
        ImageFormat termImageFormat;
        while (true) {
            printStream.print(text);
            String input = scan.nextLine();
            if (input.isEmpty()) {
                return imageFormat;
            }
            try {
                termImageFormat = ImageFormat.valueOf(input.toUpperCase());
                return termImageFormat;
            } catch (IllegalArgumentException exception) {
                printStream.println("Невернный ввод, попробуйте еще раз!");
            }
        }
    }

    public int inputParam(String inputText, Integer num, Integer limitTop) {
        printStream.print(inputText);
        while (true) {
            String input = scan.nextLine();
            try {
                if (input.isEmpty()) {
                    return num;
                }
                int termNum = Integer.parseInt(input);
                if (isValid(termNum, limitTop)) {
                    return termNum;
                }
                printStream.println("не верный размер");
            } catch (RuntimeException exception) {
                printStream.println("Введено не число, попробуйте еще раз!");
            }
            printStream.print(inputText);
        }
    }

    public void input() {
        printStream.println("При нажатии Enter - конфигурации устанавливается дефолтное значение!");
        inputSrv.height =
            inputSrv.inputParam(String.format("[%d] Введите высоту картинки: ", count++), height, MAX_SIZE);

        inputSrv.weight =
            inputSrv.inputParam(String.format("[%d] Введите ширину картинки: ", count++), weight, MAX_SIZE);

        inputSrv.sampleCount =
            inputSrv.inputParam(String.format("[%d] Введите количество выборок для рендеринга: ", count++), sampleCount,
                MAX_CONFIG);
        inputSrv.iterationCount =
            inputSrv.inputParam(String.format("[%d] Введите количество итераций для выборки: ", count++),
                iterationCount, MAX_CONFIG);
        inputSrv.spaceCount =
            inputSrv.inputParam(String.format("[%d] Введите количество аффинных пространств: ", count++), spaceCount,
                MAX_CONFIG);
        inputSrv.initializationAffineSpace();

        inputSrv.symmetryCount =
            inputSrv.inputParam(String.format("[%d] Введите количество симметрий: ", count++), symmetryCount,
                MAX_SYMMETRY_THREAD);
        inputSrv.threadCount =
            inputSrv.inputParam(String.format("[%d] Введите количество потоков: ", count++), threadCount,
                MAX_SYMMETRY_THREAD);
        inputSrv.imageFormat =
            inputSrv.inputTypeImage(String.format("[%d] Введите тип вывода картинки - {jpeg, bmp, png}: ", count++));
        printStream.println("----------------------------------");
        inputSrv.typeTransformation = inputSrv.inputTypeTransformation("Введите вид трансформации: ");
        log.info("Закончен ввод данных");
    }

    public void printInputConfig() {
        count = 1;
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
