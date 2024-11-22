package backend.academy;

public record FractalImage(Pixel[] data, int width, int height) {
//    public static FractalImage create(int width, int height) {
//        new FractalImage()
//    }
    boolean contains(int x, int y) {return true;}
    Pixel pixel(int x, int y) {
        return new Pixel(1,2,2,1);
    }
}
