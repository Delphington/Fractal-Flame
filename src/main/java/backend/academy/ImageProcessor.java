package backend.academy;

// пост-обработка in-place, например, гамма-коррекция
@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
