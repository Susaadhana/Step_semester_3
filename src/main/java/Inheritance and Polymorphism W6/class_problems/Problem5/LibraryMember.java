public class LibraryMember {

    private static int membersEnrolled = 0;

    protected int borrowLimit;
    protected int booksBorrowed;

    protected final String memberNumber;

    public LibraryMember(int borrowLimit) {
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        membersEnrolled++;
        memberNumber = "LIB-" + (100 + membersEnrolled);
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        System.out.println("Genre: " + genre);
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public static String processNightlyAudit(LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }
}