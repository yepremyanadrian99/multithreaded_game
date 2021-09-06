package am.adrian.multithreaded_game.pattern;

import java.util.Collection;
import java.util.Set;

public interface Observable {

    Set<Observer> getObservers();

    default void addObservers(Collection<? extends Observer> observers) {
        getObservers().addAll(observers);
    }

    default void addObserver(Observer observer) {
        getObservers().add(observer);
    }

    default void updateObservers() {
        getObservers().forEach(Observer::handleObserverUpdated);
    }
}
