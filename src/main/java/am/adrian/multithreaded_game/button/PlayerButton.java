package am.adrian.multithreaded_game.button;

import java.awt.Dimension;
import java.awt.Panel;
import javax.swing.JButton;

import am.adrian.multithreaded_game.enumeration.PlayerDirection;
import am.adrian.multithreaded_game.listener.GameMouseListener;
import lombok.Data;

@Data
public class PlayerButton {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private final JButton button;
    private final PlayerDirection direction;
    private final Panel panel;
    private final int x;
    private final int y;

    public PlayerButton(Panel panel, int x, int y, PlayerDirection direction) {
        this.button = new JButton();
        this.direction = direction;
        this.panel = panel;
        this.x = x;
        this.y = y;
        initButton();
    }

    public void addMouseListener(Runnable action) {
        button.addMouseListener(new GameMouseListener(action));
    }

    private void initButton() {
        button.setBounds(x, y, WIDTH, HEIGHT);
        button.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        button.setSize(WIDTH, HEIGHT);
        button.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        button.setText(direction.name());
        button.setEnabled(true);
        button.setVisible(true);
        panel.add(button);
    }
}
