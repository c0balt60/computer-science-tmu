package quizes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class ToDoList {
    private PriorityQueue<Task> queue = new PriorityQueue<>();
    private ArrayList<Task> initOrder = new ArrayList<>();

    public static void main(String[] args) {
        // Create tasks
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Task a = Task.createTask();
            System.out.println(a);
            tasks.add(a);
        }

        System.out.println("\nSORTING: \n");

        ToDoList list = new ToDoList(tasks);
        System.out.println("\nOriginal: \n" + list.original()); // Print Original ordering

        // Sort in reverse
        StringBuilder reverse = new StringBuilder();
        StringBuilder normal = new StringBuilder();

        PriorityQueue<Task> sortedRev = list.sortPriority(true);
        PriorityQueue<Task> sorted = list.sortPriority(false);

        while (!sortedRev.isEmpty())
            reverse.append(sortedRev.poll().toString() + ", ");

        while (!sorted.isEmpty())
            normal.append(sorted.poll().toString() + ", ");

        System.out.println("\nReversed: \n" + reverse);
        System.out.println("\nNormal: \n" + normal);

        // Remove all odd priority
        list.removeOdd();
        System.out.println("\nRemoved odd:\n " + list);

        // Print size
        System.out.println("Size: " + list.getSize());
    }

    public ToDoList(ArrayList<Task> tasks) {
        this.queue = new PriorityQueue<>(tasks);
        this.initOrder = new ArrayList<>(tasks);
    }

    public ToDoList() {
    }

    public int getSize() {
        return queue.size();
    }

    public void addTask(Task task) {
        queue.add(task);
        initOrder.add(task);
    }

    public Task getNextTask() {
        return queue.element();
    }

    public PriorityQueue<Task> sortPriority(boolean highLow) {
        PriorityQueue<Task> sorted = new PriorityQueue<>(highLow ? Collections.reverseOrder() : null);
        queue.forEach(task -> sorted.add(task));
        return sorted;
    }

    public void removeOdd() {
        queue.removeIf(task -> task.getPriority() % 2 != 0);
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public String original() {
        return initOrder.toString();
    }
}
