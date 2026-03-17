package quizes;

import java.util.UUID;

public class Task implements Comparable<Task> {
    // Class Factory
    public static Task createTask() {
        String description = UUID.randomUUID().toString().substring(0, 4);
        int priority = (int) (Math.random() * 100);
        int eta = (int) (Math.random() * 60);
        return new Task(description, eta, priority);
    }

    // Class fields
    private String desc;
    private int eta;
    private int priority;

    public Task(String description, int eta, int priority) {
        this.desc = description;
        this.eta = eta;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task o) {
        // Compare by priority
        int oP = o.getPriority();
        return Integer.compare(this.priority, oP);
    }

    @Override
    public String toString() {
        return String.format(
                "Task[%s, ETA: %d, Priority: %d]",
                desc,
                eta,
                priority);
        // return "Task: %d".formatted(priority);
    }
}
