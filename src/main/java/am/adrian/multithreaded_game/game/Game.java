package am.adrian.multithreaded_game.game;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import javax.swing.JFrame;

import am.adrian.multithreaded_game.joystick.Joystick;
import am.adrian.multithreaded_game.object.Player;
import am.adrian.multithreaded_game.pattern.Observer;
import am.adrian.multithreaded_game.window.PlayerWindow;
import lombok.Getter;

public class Game implements Observer {

    private volatile boolean isGameOver = false;

    @Getter
    private final Map<Player, PlayerWindow> playerFrameMap;

    public Game() {
        playerFrameMap = new HashMap<>();
    }

    @Override
    public void handleObserverUpdated() {
        playerFrameMap.values().forEach(Observer::handleObserverUpdated);
    }

    public void start() {
        isGameOver = false;
        Collection<PlayerWindow> playerWindows = playerFrameMap.values();
        ExecutorService executorService = Executors.newFixedThreadPool(playerWindows.size());
        playerWindows.forEach(executorService::execute);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void addPlayer(Player player) {
        PlayerWindow playerWindow = buildWindowForPlayer(player);
        playerFrameMap.put(player, playerWindow);
        connectJoysticksWithPlayerWindows();
    }

    private PlayerWindow buildWindowForPlayer(Player player) {
        Joystick joystick = buildJoystickForPlayer(player);
        JFrame playerWindowFrame = initJFrame(new JFrame(player.getName()), 500, 500);
        PlayerWindow playerWindow = new PlayerWindow(this, player, playerWindowFrame, joystick);
        joystick.addObserver(playerWindow);
        return playerWindow;
    }

    private Joystick buildJoystickForPlayer(Player player) {
        Panel joystickPanel = new Panel(new GridLayout(2, 2));
        return new Joystick(joystickPanel, player);
    }

    private JFrame initJFrame(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(width, height);
        frame.setVisible(true);
        return frame;
    }

    private void connectJoysticksWithPlayerWindows() {
        Collection<PlayerWindow> playerWindows = playerFrameMap.values();
        Set<Joystick> joysticks = playerWindows.stream()
            .map(PlayerWindow::getJoystick)
            .collect(Collectors.toSet());
        joysticks.forEach(joystick -> joystick.addObservers(playerWindows));
    }
}
