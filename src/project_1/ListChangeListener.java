package project_1;

public interface ListChangeListener {
    public enum Action {
        ADDED,
        REMOVED,
        CLEARED
    }

    void onListChanged(Action action, Object item, int newSize);
}
