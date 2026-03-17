
import labs.Polynomial;
import quizes.Course;
import quizes.Student;
import quizes.Course.ClassType;
import quizes.Course.TimeType;

public class App {
    public static void main(String[] args) {
        // region Polynomial
        // int[] c6 = { 7, -5, 3 };
        // int[] c7 = { 6, 0, 0, -4 };
        // Polynomial p1 = new Polynomial(c6);
        // Polynomial p2 = new Polynomial(c7);
        // System.out.println(p1);
        // System.out.println(p2);
        // System.out.println("Multiply: " + p1.multiply(p2));
        // System.out.println("Multiply: " + p2.multiply(p1));

        int[] c1 = { -6, 99, 11, 12 };
        int[] c2 = { 6, -99, 11, 12 };
        int[] c3 = { 42, 10000000 };
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);
        Polynomial p3 = new Polynomial(c3);
        System.out.println(p1.compareTo(p2));
        System.out.println(p2.compareTo(p1));
        // endregion

        // region Quiz 3
        // Create courses: CPS383, CPS510, plus the current one and the pre-requisite
        // one.
        // Atleast one scheudling conflict

        // Course cps383 = new Course("cps383");
        // Course cps510 = new Course("cps510");
        // Course cps209 = new Course("cps209");
        // Course cps109 = new Course("cps109");

        // // Schedules
        // cps383.SetTime(ClassType.LECTURE, TimeType.START, 8); // 8am
        // cps383.SetTime(ClassType.LECTURE, TimeType.END, 10); // 10am

        // cps510.SetTime(ClassType.LECTURE, TimeType.START, 12); // 12pm
        // cps510.SetTime(ClassType.LECTURE, TimeType.END, 14); // 2pm

        // // Conflicting time
        // cps209.SetTime(ClassType.LECTURE, TimeType.START, 10); // 10am
        // cps209.SetTime(ClassType.LECTURE, TimeType.END, 12); // 12am
        // cps209.SetTime(ClassType.LAB, TimeType.START, 11); // 10am
        // cps209.SetTime(ClassType.LAB, TimeType.END, 14); // 11am
        // endregion

        // region Quiz 4
        // Student alex = new Student("Alex", 18);

        // alex.addCourse("CPS209");
        // alex.addCourse("CPS412");

        // System.out.println(alex);
        // endregion

    }

}
