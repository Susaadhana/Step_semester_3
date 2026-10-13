import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[Email → " + student.getName()
                + "] " + notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[SMS → " + student.getName()
                + "] " + notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student student, Notice notice) {
        System.out.println("[App → " + student.getName()
                + "] " + notice.getTitle());
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels = new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {
    private String title;
    private Set<String> departments;

    Notice(String title, Set<String> departments) {
        this.title = title;
        this.departments = new HashSet<>(departments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(String title, String... departments) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Cannot post notice: Title is required.");
            return;
        }

        Set<String> targets = new LinkedHashSet<>();

        for (String department : departments) {
            if (department != null && !department.trim().isEmpty()) {
                targets.add(department);
            }
        }

        if (targets.isEmpty()) {
            System.out.println(
                    "Cannot post notice: At least one target department is required.");
            return;
        }

        Notice notice = new Notice(title, targets);

        System.out.println("Notice '" + title + "' posted to "
                + String.join(", ", targets) + ".");

        for (Student student : students) {
            if (notice.getDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.postNotice("Lab Closed Tomorrow", "CSE");
        board.postNotice("Fee Deadline Extended", "CSE", "ECE");
        board.postNotice("Sports Day");
    }
}