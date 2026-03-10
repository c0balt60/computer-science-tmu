package project_1;

import java.util.ArrayList;

public class Student extends Person {
    // Factory student generation
    private static String[] firstNames = { "Jake", "Bob", "Alex", "John", "Ethan", "Matthew", "Josh", "Phil" };
    private static String[] majors = { "Computer Science", "Engineering", "BioMed", "Math", "Buisness" };

    /**
     * Generates a random student for quick testing
     *
     * @return {@code Student}
     */
    public static Student createRandom() {
        String name = firstNames[(int) (Math.random() * firstNames.length)];
        String major = majors[(int) (Math.random() * majors.length)];
        int age = (int) (Math.random() * 4) + 18;
        float gpa = Math.round((Math.random() * 2 + 2) * 10f) / 10f;
        int year = (int) (Math.random() * 4);
        return new Student(name, age, major, year, gpa);
    }

    // Class fields
    private String major;
    private int year;
    private float gpa;
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private ArrayList<Course> completedCourses = new ArrayList<>();

    /**
     * Creates Student with just their Major
     *
     * @param name
     * @param age
     * @param major
     * @see {@link #Student(String, int, String, int, float)}
     */
    public Student(String name, int age, String major) {
        this(name, age, major, 1, 0f);
    }

    /**
     * Creates a Student
     *
     * @param name
     * @param age
     * @param major
     * @param year
     * @param gpa
     */
    public Student(String name, int age, String major, int year, float gpa) {
        super(name, age);
        this.major = major;
        this.year = year;
        this.gpa = gpa;
    }
}
