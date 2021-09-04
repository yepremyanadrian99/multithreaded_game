package am.adrian.multithreaded_game.joystick;

import java.awt.Panel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import am.adrian.multithreaded_game.button.PlayerButton;
import am.adrian.multithreaded_game.enumeration.PlayerDirection;
import am.adrian.multithreaded_game.object.Player;
import am.adrian.multithreaded_game.window.PlayerWindow;

public class Joystick {

    private final Player player;
    private final Set<PlayerButton> buttons;
    private final PlayerWindow playerWindow;
    private final Panel panel;

    public Joystick(PlayerWindow playerWindow, Panel panel, Player player) {
        this.playerWindow = playerWindow;
        this.panel = panel;
        this.player = player;
        this.buttons = new HashSet<>();
        initPlayerButtons();
    }

    private void initPlayerButtons() {
        Arrays.asList(PlayerDirection.values())
            .forEach(direction -> buttons.add(new PlayerButton(panel, 0, 0, direction)));
        initPlayerButtonsListeners(player);
        playerWindow.triggerUpdate();
    }

    private void initPlayerButtonsListeners(Player player) {
        buttons.forEach(playerButton ->
            playerButton.addMouseListener(() -> {
                Runnable playerAction = getPlayerActionByDirection(player, playerButton.getDirection());
                playerAction.run();
                playerWindow.getGame().triggerUpdate();
            }));
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
