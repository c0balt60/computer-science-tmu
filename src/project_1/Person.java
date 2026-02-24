package project_1;

public class Person {
    private String name;
    private int age;
    private int id;

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Person(String name, int age) {
        this(name, age, (int) (Math.random() * 1000 + 500));
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getId() {
        return this.id;
    }

    public boolean equals(Person other) {
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return String.format("Name: %s; ID: %s", this.name, this.id);
    }
}
