public class MembershipTest {

    public static String classifyGeneration(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentMember) {
            return "Student branch";
        }

        return "General Member";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }
}