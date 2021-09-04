package am.adrian.multithreaded_game.game;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

import am.adrian.multithreaded_game.object.Player;
import am.adrian.multithreaded_game.window.PlayerWindow;
import lombok.Getter;

public class Game {

    private boolean gameOver = false;
    @Getter
    private final Map<Player, PlayerWindow> playerFrameMap;

    public Game() {
        playerFrameMap = new HashMap<>();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void addPlayer(Player player) {
        PlayerWindow playerWindow = buildWindowForPlayer(player);
        playerFrameMap.put(player, playerWindow);
        // not a good idea to initialize threads like this and throw them,
        // but I mean, for demonstration purposes, it should be fine...
        new Thread(playerWindow).start();
    }

    public void triggerUpdate() {
        playerFrameMap.values().forEach(PlayerWindow::triggerUpdate);
    }

    private PlayerWindow buildWindowForPlayer(Player player) {
        JFrame frame = new JFrame(player.getName());
        return new PlayerWindow(this, player, initJFrame(frame, 500, 500));
    }

    private JFrame initJFrame(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(width, height);
        frame.setVisible(true);
        return frame;
    }
}
