public class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit,
                         String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Faculty Member | Department: " + department +
                        " | Books Borrowed: " + booksBorrowed
        );
    }

    public String getDepartment() {
        return department;
    }
}