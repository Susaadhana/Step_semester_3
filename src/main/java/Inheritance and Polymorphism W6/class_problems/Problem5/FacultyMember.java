public class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}