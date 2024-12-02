import backend.academy.model.FractalImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FractalImageTest {

    @Test
    public void pixelTest() {
        FractalImage fractalImage = FractalImage.create(100, 100);
        assertThrows(RuntimeException.class, () -> fractalImage.pixel(10, 1010));
    }
}
