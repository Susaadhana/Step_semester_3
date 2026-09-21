public class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit,
                         String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}