package am.adrian;

import am.adrian.game.Game;
import am.adrian.game.Player;

public class Application {

    private static final int PLAYER_COUNT = 3;

    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 0; i < PLAYER_COUNT; ++i) {
            game.addPlayer(new Player("Player " + i, 50 + i * 50, 100, 10, 30));
        }
    }
}
