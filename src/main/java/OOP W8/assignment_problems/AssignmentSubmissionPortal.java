import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected double maxMarks;
    protected LocalDate dueDate;

    Assignment(String title, double maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        return Math.max(0, marks * (1 - 0.10 * lateDays));
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        return Math.max(0, marks * (1 - 0.20 * lateDays));
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status = "Not Submitted";
    private double finalMarks;

    Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
    }

    public void submit(LocalDate date) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle()
                    + "' has already been graded.");
            return;
        }

        if (status.equals("Submitted")) {
            System.out.println("Assignment has already been submitted.");
            return;
        }

        submissionDate = date;
        status = "Submitted";

        long lateDays = Math.max(0,
                ChronoUnit.DAYS.between(assignment.dueDate, submissionDate));

        System.out.println(student.getName() + "'s submission for '"
                + assignment.getTitle() + "' received ("
                + (lateDays == 0 ? "on time" : lateDays + " days late")
                + "). Status: " + status + ".");
    }

    public void grade(double marks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade: assignment has not been submitted.");
            return;
        }

        if (marks < 0 || marks > assignment.maxMarks) {
            System.out.println("Invalid marks.");
            return;
        }

        long lateDays = Math.max(0,
                ChronoUnit.DAYS.between(assignment.dueDate, submissionDate));

        finalMarks = assignment.applyPenalty(marks, lateDays);
        status = "Graded";

        System.out.printf("%s graded: %.0f/%.0f", student.getName(),
                finalMarks, assignment.maxMarks);

        if (lateDays > 0) {
            System.out.printf(" after %.0f%% late penalty", lateDays
                    * (assignment instanceof CodingAssignment ? 10 : 20));
        }

        System.out.println(". Status: " + status + ".");
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab", 50, LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(asha, coding);
        Submission s2 = new Submission(ravi, written);

        s1.submit(LocalDate.of(2026, 3, 10));
        s2.submit(LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);

        s1.submit(LocalDate.of(2026, 3, 15));
    }
}