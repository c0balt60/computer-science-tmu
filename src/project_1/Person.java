package project_1;

import java.util.Objects;
import java.util.UUID;

/**
 * Person class used as base blueprint for all characters
 */
public class Person {
    private String name;
    private int age;
    private String id;
    private String email;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = UUID.randomUUID().toString();
        this.email = "%s@university.edu".formatted(name.toLowerCase());
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean equals(Object other) {
        // Check for reference
        if (this == other)
            return true;

        // Check null and compatability
        if (other == null || getClass() != other.getClass())
            return false;

        // Cast to this class
        Person o = (Person) other;

        // Compare id and name as final check
        return this.id == o.id && Objects.equals(name, o.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("Name: %s; ID: %s; Email: %s", this.name, this.id, this.email);
    }
}
