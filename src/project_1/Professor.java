package project_1;

import java.util.ArrayList;

/**
 * Creates a professor that teaches a couple courses
 */
public class Professor extends Person {
    // Factory student generation
    private static String[] firstNames = { "Jake", "Bob", "Alex", "John", "Ethan", "Matthew", "Josh", "Phil" };

    /**
     * Generates a random student for quick testing
     *
     * @return {@code Student}
     */
    public static Professor createRandom() {
        String name = firstNames[(int) (Math.random() * firstNames.length)];
        String departments = CourseRegistry.departments[(int) (Math.random() * CourseRegistry.departments.length)];
        int age = (int) (Math.random() * 4) + 18;
        int experience = (int) (Math.random() * 10 + 5);
        return new Professor(name, age, departments, experience);
    }

    // Class fields
    private String department;
    private ArrayList<Course> assignedCourses = new ArrayList<>();
    private int xp;

    /**
     * Creates a professor object
     *
     * @param name
     * @param age
     * @param department
     * @param title
     * @param yearsOfExperience
     */
    public Professor(String name, int age, String department, int yearsOfExperience) {
        super(name, age);
        this.department = department;
        this.xp = yearsOfExperience;
    }

    public String getDepartment() {
        return this.department;
    }

    public int getYearsExperience() {
        return xp;
    }

    @Override
    public String getName() {
        return String.format("Prof. %s", super.getName());
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void assignCourse(Course c) {
        assignedCourses.add(c);
        c.setProfessor(this);
    }
}
