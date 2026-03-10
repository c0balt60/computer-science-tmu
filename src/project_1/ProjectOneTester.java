package project_1;

import java.util.ArrayList;

public class ProjectOneTester {
    /**
     * University course registration is a complex process that requires
     * coordination from
     * multiple different aspects, such as the professors (who will teach the
     * course?), students (who will take the course),
     * and the university it self (is it offering the course?).
     * All of these aspects must work together neatly to provide perfect user
     * experience and reduce
     * course problems for students and professors.
     * I have created a program that actively manages students and professors
     * enrollment in courses,
     * capacity of courses and waitlists, automatic waitlist management, and a full
     * report of the university.
     *
     */
    public static void main(String[] args) {
        // Generate courses
        ArrayList<Course> courses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            courses.add(Course.createRandom());
        }

        // Generate students
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            students.add(Student.createRandom());
        }

        // Generate profs
        ArrayList<Professor> professors = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            professors.add(Professor.createRandom());
        }

        // Create registry with people
        CourseRegistry registry = new CourseRegistry("Online University", courses, students, professors);
        System.out.println(registry);
    }
}
