package am.adrian.multithreaded_game.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener {

    private final Runnable action;

    public GameMouseListener(Runnable action) {
        this.action = action;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        action.run();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }
}
