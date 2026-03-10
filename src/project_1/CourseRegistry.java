package project_1;

import java.util.ArrayList;
import java.util.List;

import project_1.Course.STATUS;

/**
 * Main runtime for registration system
 *
 * <p>
 * Registry is responsible for controlling, managing, and updating state
 * of {@code Student}s, {@code Professor}s, and {@code Course}s.
 * </p>
 *
 * <b>IMPORTAMT</b>
 * Courses need to be registered to a professor before anything else is executed
 *
 * @author Andrii Naumenko
 * @version 0.1.0
 */
public class CourseRegistry implements ListChangeListener {
    public static String[] departments = { "Computer Science", "Biology", "Engineering", "Physics", "Medical" };

    private String universityName;
    private ArrayList<Course> availableCourses;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Professor> faculty;
    private static int totalEnrollment = 0; // NOTE - This probably is not needed

    // region Constructors
    /**
     * Creates a CourseRegistry to control a university workflow
     *
     * @param name
     * @param courses
     * @param students
     * @param professors
     */
    public CourseRegistry(String name, ArrayList<Course> courses, ArrayList<Student> students,
            ArrayList<Professor> professors) {
        this.universityName = name;
        this.availableCourses = courses;
        this.registeredStudents = students;
        this.faculty = professors;

        // Assign professors to courses
        this.assignProfessors();
        this.assignStudents();
    }

    /**
     * Creates a CourseRegistry without professors
     *
     * @param name
     * @param courses
     * @param students
     * @see {@link #CourseRegistry(String, ArrayList, ArrayList, ArrayList)}
     */
    public CourseRegistry(String name, ArrayList<Course> courses, ArrayList<Student> students) {
        this(name, courses, students, new ArrayList<>());
    }

    /**
     * Creates a CourseRegistry without professors or students
     *
     * @param name
     * @param courses
     * @see {@link #CourseRegistry(String, ArrayList, ArrayList, ArrayList)}
     */
    public CourseRegistry(String name, ArrayList<Course> courses) {
        this(name, courses, new ArrayList<>());
    }

    /**
     * Creates an empty CourseRegistry
     *
     * @param name
     * @see {@link #CourseRegistry(String, ArrayList, ArrayList, ArrayList)}
     */
    public CourseRegistry(String name) {
        this(name, new ArrayList<>());
    }
    // endregion

    /**
     * Name of the university
     *
     * @return {@code String} name of the university
     */
    public String getName() {
        return universityName;
    }

    /**
     * Adds a course to available courses
     *
     * @param c {@code Course} to make available
     */
    public void addCourse(Course c) {
        availableCourses.add(c);
    }

    /**
     * Registers a student
     *
     * @param s {@code Student} to register
     */
    public void addStudent(Student s) {
        registeredStudents.add(s);
    }

    /**
     * Adds a professor to the faculty
     *
     * @param p {@code Professor} to add
     */
    public void addFaculty(Professor p) {
        faculty.add(p);
    }

    /**
     * Finds and returns the {@code Course} for given course code
     *
     * @param code the code of the course
     * @return {@code Course} | null
     */
    public Course findCourseByCode(String code) {
        for (Course c : availableCourses)
            if (c.getCourseCode() == code)
                return c;
        return null;
    }

    /**
     * Returns a {@code Student} for the given (String) UUID
     *
     * @param id UUID converted to string
     * @return {@code Student}
     */
    public Student findStudentById(String id) {
        for (Student s : registeredStudents)
            if (s.getId() == id)
                return s;
        return null;
    }

    /**
     * Returns a list of currently open courses
     *
     * @return {@code List<Course>} List of all open courses
     */
    public List<Course> getOpenCourses() {
        return availableCourses.stream().filter(c -> !c.isFull()).toList();
    }

    /**
     * Registers a professor to every course
     * <p>
     * <del>If course > professors, random professors will be assigned to
     * courses</del><br>
     * Reduced complexity by just selecting random professors
     * </p>
     */
    public void assignProfessors() {
        if (availableCourses.size() < 1)
            return;
        availableCourses.forEach(
                course -> {
                    Professor selected = faculty.get((int) (Math.random() * faculty.size()));
                    course.setProfessor(selected);
                    selected.assignCourse(course);
                });
    }

    /**
     * Assigns students to courses
     */
    public void assignStudents() {
        if (availableCourses.size() < 1)
            return;
        registeredStudents.forEach(student -> {
            ArrayList<Course> courses = new ArrayList<Course>(availableCourses);
            // Register into full course load (5 courses, unless there are less than 5)
            for (int i = 0; i < 5; i++) {
                Course course = courses.get((int) (Math.random() * courses.size()));
                STATUS status = course.enroll(student);
                courses.remove(course);
            }
        });
    }

    /**
     * Listens for internal changes on Observable Lists
     *
     * @param action
     * @param item
     * @param newSize
     */
    @Override
    public void onListChanged(Action action, Object item, int newSize) {
        System.out.println("New: " + action + " " + item);
    }

    @Override
    public String toString() {
        String header = String.format(
                "%s [Students: %d, Faculty: %d, Courses: %d]",
                universityName,
                registeredStudents.size(),
                faculty.size(),
                availableCourses.size());

        String courses = String.format(
                "\t%s\n".repeat(availableCourses.size()),
                availableCourses
                        .stream()
                        .map(course -> course.toString())
                        .toArray());

        return String.format(
                "%s\n\n%s",
                header,
                courses);
    }
}
