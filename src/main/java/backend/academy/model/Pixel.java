package backend.academy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pixel {
    private int r;
    private int g;
    private int b;
    private volatile int cnt = 0; // Счетчик обращений к пикселю
    private double normal = 0;

    public Pixel(int r, int g, int b, int hitCount) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.cnt = hitCount;
    }

    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setCorrect(int r, int g, int b) {
        this.r = (this.r + r) / 2;
        this.g = (this.g + g) / 2;
        this.b = (this.b + b) / 2;
    }
}
