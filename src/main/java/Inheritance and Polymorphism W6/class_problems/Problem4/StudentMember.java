public class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit,
                         String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Student | Course: " + course +
                        " | Books: " + booksBorrowed
        );
    }
}