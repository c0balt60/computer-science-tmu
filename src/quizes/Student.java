package quizes;

import java.util.HashSet;

public class Student extends Person {

    private HashSet<String> courses = new HashSet<>();

    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        // Compress courses
        StringBuilder compress = new StringBuilder();
        this.courses.forEach(str -> compress.append("\n" + str));

        return String.format(
                "Student Name: %s\nStudent Age: %d\nCourses: %s",
                this.getName(), this.getAge(), compress);
    }

    public void addCourse(String name) {
        this.courses.add(name);
    }

    public void removeCourse(String name) {
        // Check exists
        if (!this.courses.contains(name))
            return;
        this.courses.remove(name);
    }
}
