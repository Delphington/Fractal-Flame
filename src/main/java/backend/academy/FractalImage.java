package backend.academy;

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
        return data[y][x];
    }
    // Метод для проверки, находится ли точка внутри границ изображения
    public boolean isInImage(Point point) {
        return point.x() >= 0 && point.x() < width && point.y() >= 0 &&
            point.y() < height; // Проверяем, что координаты точки находятся в пределах ширины и высоты изображения
    }
}
