package am.adrian.multithreaded_game;

import am.adrian.multithreaded_game.game.Game;
import am.adrian.multithreaded_game.object.Player;

public class Application {

    private static final int PLAYER_COUNT = 3;

    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 0; i < PLAYER_COUNT; ++i) {
            game.addPlayer(new Player("Player " + i, 50 + i * 50, 100, 10, 30));
        }
        game.start();
    }
}
