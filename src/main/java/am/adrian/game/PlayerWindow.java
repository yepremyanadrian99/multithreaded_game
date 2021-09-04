package am.adrian.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JFrame;

import am.adrian.game.joystick.Joystick;
import lombok.Getter;

public class PlayerWindow extends Component implements Runnable {

    @Getter
    private final Game game;
    private final Player player;
    private final JFrame frame;
    private final Joystick joystick;
    private final Panel mainPanel;
    private final Panel gamePanel;
    private final Panel joystickPanel;
    private boolean isUpdated = true;

    public PlayerWindow(Game game, Player player, JFrame frame) {
        this.game = game;
        this.player = player;
        this.frame = frame;
        this.mainPanel = new Panel(new GridLayout(2, 0));
        this.gamePanel = new Panel();
        this.joystickPanel = new Panel(new GridLayout(2, 2));
        this.frame.setContentPane(mainPanel);
        this.mainPanel.add(gamePanel);
        this.mainPanel.add(joystickPanel);
        this.frame.add(this);
        this.joystick = new Joystick(this, joystickPanel, player);
    }

    @Override
    public void run() {
        while (!game.isGameOver()) {
            if (isUpdated) {
                this.mainPanel.repaint();
                isUpdated = false;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        game.getPlayerFrameMap().keySet().forEach(eachPlayer -> {
                if (eachPlayer == player) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.RED);
                }
                g.fillRect(eachPlayer.getX(), eachPlayer.getY(), eachPlayer.getWidth(), eachPlayer.getHeight());
            }
        );
    }

    public void triggerUpdate() {
        isUpdated = true;
    }
}
