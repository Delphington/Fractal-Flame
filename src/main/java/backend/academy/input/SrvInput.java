package backend.academy.input;

import lombok.Getter;
import java.io.PrintStream;
import java.util.Scanner;

public class SrvInput {
    private PrintStream printStream = System.out;
    private Scanner scan = new Scanner(System.in);

    @Getter
    private int height = 1080;
    @Getter
    private int weight = 1920;
    @Getter
    private int sampleCount = 2000;
    @Getter
    private int iterationCount = 2000;
    @Getter
    private int symmetryCount = 20;
    @Getter
    private int threadCount = 6;




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
