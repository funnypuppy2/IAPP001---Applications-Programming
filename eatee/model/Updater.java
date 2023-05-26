package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class to update views
 *
 */
public class Updater {

    private List<MyObserver> observers = new ArrayList<>();

    public void registerObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void updateViews() {
        for (MyObserver observer : observers) {
            observer.update();
        }
    }
}
