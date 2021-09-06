package am.adrian.multithreaded_game.joystick;

import java.awt.Panel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import am.adrian.multithreaded_game.button.PlayerButton;
import am.adrian.multithreaded_game.enumeration.PlayerDirection;
import am.adrian.multithreaded_game.listener.GameMouseListener;
import am.adrian.multithreaded_game.object.Player;
import am.adrian.multithreaded_game.pattern.Observable;
import am.adrian.multithreaded_game.pattern.Observer;
import lombok.Getter;

public class Joystick implements Observable {

    @Getter
    private final Panel panel;
    private final Player player;
    private final Set<PlayerButton> buttons;
    @Getter
    private final Set<Observer> observers;

    public Joystick(Panel panel, Player player) {
        this.panel = panel;
        this.player = player;
        this.buttons = new HashSet<>();
        this.observers = new HashSet<>();
        initPlayerButtons();
    }

    private void initPlayerButtons() {
        Arrays.asList(PlayerDirection.values())
            .forEach(direction -> buttons.add(new PlayerButton(panel, 0, 0, direction)));
        initPlayerButtonsListeners(player);
        updateObservers();
    }

    private void initPlayerButtonsListeners(Player player) {
        buttons.forEach(playerButton ->
            playerButton.addMouseListener(new GameMouseListener(() -> {
                Runnable playerAction = getPlayerActionByDirection(player, playerButton.getDirection());
                playerAction.run();
                updateObservers();
            })));
    }

    private static Runnable getPlayerActionByDirection(Player player, PlayerDirection direction) {
        if (direction == PlayerDirection.UP) {
            return player::up;
        } else if (direction == PlayerDirection.DOWN) {
            return player::down;
        } else if (direction == PlayerDirection.LEFT) {
            return player::left;
        } else if (direction == PlayerDirection.RIGHT) {
            return player::right;
        }
        throw new RuntimeException("Missing action for direction: " + direction);
    }
}
