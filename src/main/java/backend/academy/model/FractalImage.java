package backend.academy.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public record FractalImage(Pixel[][] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        Pixel[][] table = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                table[i][j] = new Pixel();
            }
        }
        return new FractalImage(table, width, height);
    }

    public Pixel pixel(int x, int y) {
        if(x>=0 && x<=width &&  y>=0 && y<height) {
            return data[y][x];
        }
        log.warn("Не корретный запрос пикселя в классе FractalImage.pixel");
        throw new RuntimeException("Не корретный запрос");
    }
}
