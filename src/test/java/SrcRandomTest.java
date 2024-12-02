import backend.academy.SrcRandom;
import backend.academy.model.Point;
import backend.academy.render.Rect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SrcRandomTest {

    @Test
    @DisplayName("Число находится в верном диапозоне")
    public void getRandomIntTest() {
        for (int i = 0; i < 1000; i++) {
            int randomInt = SrcRandom.getRandomInt(10, 20);
            assertTrue(randomInt >= 10 && randomInt < 20);
        }
    }

    @Test
    @DisplayName("Граничные случаи")
    public void getRandomIntEdgeCasesTest() {
        assertEquals(10, SrcRandom.getRandomInt(10, 10.6));
        assertEquals(10, SrcRandom.getRandomInt(10, 10.1));
        assertEquals(10, SrcRandom.getRandomInt(10, 11));
    }

    @Test
    @DisplayName("Проверка генерации точки в пределе")
    public void randomPointInRectTest() {
        Rect rect = new Rect(10, 10, 50, 50);

        for (int i = 0; i < 100; i++) {
            Point point = SrcRandom.random(rect);
            assertTrue(point.x() >= 10 && point.x() < 60);
            assertTrue(point.y() >= 10 && point.y() < 60);
        }
    }

    @Test
    @DisplayName("Проверка передачи null")
    public void randomPointNullRectTest() {
        assertThrows(NullPointerException.class, () -> SrcRandom.random(null));
    }
}
