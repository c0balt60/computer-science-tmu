package project_1;

import java.util.ArrayList;

public class Course implements Comparable<Course>, ListChangeListener {
    public enum STATUS {
        ENROLLED,
        WAITLISTED,
        FAILED
    }

    // Factory student generation
    private static String[] courseCodes = { "CPS109,Intro to Computer Science", "CPS209,Object Oriented Programming",
            "PCS110,Intro to Physics", "CHY110,Intro to Chemistry", "MED101,Intro to medical studies",
            "CPS310,Computer Organization", "CPS412,Social Issues and Ethics", "SOC302,Popular Culture",
            "MTH110,Intro to mathematics", "MTH208,Advanced math" };

    /**
     * Generates a random student for quick testing
     *
     * @return {@code Student}
     */
    public static Course createRandom() {
        String[] courseCode = courseCodes[(int) (Math.random() * courseCodes.length)].split(",");
        String department = CourseRegistry.departments[(int) (Math.random() * CourseRegistry.departments.length)];
        int capacity = (int) (Math.random() * 50 + 30);
        return new Course(courseCode[0], courseCode[1], department, capacity);
    }

    // Class fields
    private String courseCode;
    private String courseName;
    private String department;
    private int capacity;
    private ObservableList<Student> enrolledStudents = new ObservableList<>();
    private ArrayList<Student> waitlist = new ArrayList<>();
    private Professor instructor;
    private boolean isOpen = true;

    /**
     * Creates a course object
     *
     * @param courseCode
     * @param courseName
     * @param department
     * @param capacity
     */
    public Course(String courseCode, String courseName, String department, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.department = department;
        this.capacity = capacity;

        // Add listener
        enrolledStudents.addListener(this);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public String getDepartment() {
        return department;
    }

    public void setProfessor(Professor p) {
        instructor = p;
    }

    /**
     * Checks capacity of course and enrolls student
     **/
    public STATUS enroll(Student s) {
        if (enrolledStudents.size() > capacity) {
            waitlist.add(s);
            return STATUS.WAITLISTED;
        }
        enrolledStudents.add(s);
        return STATUS.ENROLLED;
    }

    /**
     * Drops student and enrolls next on waitlist
     **/
    public void drop(Student s) {
        enrolledStudents.remove(s);

        if (waitlist.size() > 0) {
            Student next = waitlist.get(0);
            enrolledStudents.add(next);
            waitlist.remove(next);
        }
    }

    /**
     * Check if class capacity is maxxed
     **/
    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    /**
     * Used for registry report
     **/
    public int getEnrollmentPercent() {
        return (int) (((float) enrolledStudents.size() / (float) capacity) * 100);
    }

    @Override
    public int compareTo(Course other) {
        int code = Integer.parseInt(courseCode.substring(3));
        int oCode = Integer.parseInt(other.getCourseCode().substring(3));

        if (code > oCode) {
            return 1;
        } else if (code < oCode) {
            return -1;
        }

        if (courseCode.charAt(0) > other.getCourseCode().charAt(0)) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format(
                "%s - %s [Enrolled: %d, Waitlist: %d, Enrolled: %d%%, Instructor: %s]",
                courseCode,
                courseName,
                enrolledStudents.size(),
                waitlist.size(),
                getEnrollmentPercent(),
                instructor.getName());
    }

    /**
     * Simple method of listening to changes in enrolledStudents
     */
    @Override
    public void onListChanged(Action action, Object item, int newSize) {
        isOpen = (newSize > capacity) ? false : true;
    }
}
