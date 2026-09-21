public class Report {

    public static String batchPrint(LibraryMember[] members) {

        StringBuilder result = new StringBuilder();

        for (LibraryMember member : members) {

            member.displayInfo();

            result.append(
                    member instanceof StudentMember
                            ? "Student | Course: "
                            + ((StudentMember) member).getCourse()
                            + " | Books: "
                            + member.getBooksBorrowed()
                            + " [Course via downcast: "
                            + ((StudentMember) member).getCourse()
                            + "] | "
                            : "General | Books: "
                            + member.getBooksBorrowed()
                            + " | "
            );
        }

        return result.toString();
    }
}