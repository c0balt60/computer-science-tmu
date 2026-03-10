package project_1;

import java.util.ArrayList;

import project_1.ListChangeListener.Action;

public class ObservableList<T> {
    private ArrayList<T> list = new ArrayList<>();
    private ArrayList<ListChangeListener> listeners = new ArrayList<>();

    private void notifyListeners(Action action, T o) {
        listeners.forEach(listener -> listener.onListChanged(action, o, list.size()));
    }

    // Add listener to update loop
    public void addListener(ListChangeListener listener) {
        listeners.add(listener);
    }

    public void add(T o) {
        list.add(o);
        notifyListeners(Action.ADDED, o);
    }

    public void remove(T o) {
        list.remove(o);
        notifyListeners(Action.REMOVED, o);
    }

    public int size() {
        return list.size();
    }
}
