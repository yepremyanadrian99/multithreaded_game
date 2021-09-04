package am.adrian.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {

    private static final int DX = 10;
    private static final int DY = 10;

    private final String name;
    private int x;
    private int y;
    private int width;
    private int height;

    public void up() {
        y -= DY;
    }

    public void down() {
        y += DY;
    }

    public void left() {
        x -= DX;
    }

    public void right() {
        x += DX;
    }
}
