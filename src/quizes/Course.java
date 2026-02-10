// QuizTwo
package quizes;

public class Course {

    public enum ClassType {
        LECTURE,
        LAB,
    }

    public enum TimeType {
        START,
        END,
    }

    private String code;

    private int lecture_start;
    private int lecture_end;

    private int lab_start;
    private int lab_end;

    private int year;
    private String semester;

    public Course(String course_code) {
        code = course_code;
    }

    // Private
    private boolean IsConflictingLecture(TimeType time, int newTime) {
        switch (time) {
            case START:
                return !(newTime >= lab_end || lecture_end <= lab_start);

            case END:
                return !(lecture_start >= lab_end || newTime <= lab_start);

            default:
                return true;
        }
    }

    private boolean IsConflictingLab(TimeType time, int newTime) {
        switch (time) {
            case START:
                return !(newTime >= lecture_end || lab_end <= lecture_start);

            case END:
                return !(lab_start >= lecture_end || newTime <= lecture_start);

            default:
                return false;
        }
    }

    // Setters
    public void SetTime(ClassType classType, TimeType timeType, int time) {
        // Check
        switch (classType) {
            case LECTURE:
                if (IsConflictingLecture(timeType, time))
                    System.out.println(
                            "Conflicting times (lab): time:"
                                    + time
                                    + "  "
                                    + " lab_s: " + lab_start
                                    + " lab_e: " + lab_end
                                    + " lec_S: " + lecture_start
                                    + " lec_e: " + lecture_end
                                    + "  @:" + code);

                if (timeType == TimeType.START)
                    lecture_start = time;
                else
                    lecture_end = time;
                return;

            case LAB:
                if (IsConflictingLab(timeType, time))
                    System.out.println(
                            "Conflicting times (lab): time:"
                                    + time
                                    + "  "
                                    + " lab_s: " + lab_start
                                    + " lab_e: " + lab_end
                                    + " lec_S: " + lecture_start
                                    + " lec_e: " + lecture_end
                                    + "  @:" + code);

                if (timeType == TimeType.START)
                    lab_start = time;
                else
                    lab_end = time;
                return;

            default:
                break;

        }
    }

    // Getters
    public int GetLectureStart() {
        return lecture_start;
    }

    public int GetLectEnd() {
        return lecture_end;
    }

    public int GetLabStart() {
        return lab_start;
    }

    public int GetLabEnd() {
        return lab_end;
    }

    public int GetYear() {
        return year;
    }

    public String GetSemester() {
        return semester;
    }

    public String GetCourseCode() {
        return code;
    }

}
