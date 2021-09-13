package am.adrian.multithreaded_game.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JFrame;

import am.adrian.multithreaded_game.game.Game;
import am.adrian.multithreaded_game.joystick.Joystick;
import am.adrian.multithreaded_game.object.Player;
import am.adrian.multithreaded_game.pattern.Observer;
import lombok.Getter;

public class PlayerWindow extends Component implements Runnable, Observer {

    @Getter
    private final Game game;
    private final Player player;
    private final JFrame frame;
    private final Panel mainPanel;
    @Getter
    private final Joystick joystick;
    private volatile boolean isUpdated = true;

    public PlayerWindow(Game game, Player player, JFrame frame, Joystick joystick) {
        this.game = game;
        this.player = player;
        this.frame = frame;
        this.joystick = joystick;
        this.mainPanel = new Panel(new GridLayout(2, 0));
        this.frame.setContentPane(mainPanel);
        this.mainPanel.add(new Panel());
        this.mainPanel.add(joystick.getPanel());
        this.frame.add(this);
    }

    @Override
    public void handleObserverUpdated() {
        isUpdated = true;
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

    @Override
    public void run() {
        while (!game.isGameOver()) {
            if (isUpdated) {
                this.mainPanel.repaint();
                isUpdated = false;
            }
        }
    }
}
