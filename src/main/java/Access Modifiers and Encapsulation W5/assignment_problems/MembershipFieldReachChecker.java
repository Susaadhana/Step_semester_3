class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE") ||
                    accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";
            return "DENIED";
        }

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        String result = "";

        for (int i = 0; i < modifiers.length; i++) {
            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {
                if (attempt[0].equals(modifiers[i])) {
                    if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            if (i > 0)
                result += " | ";

            result += modifiers[i] + ": " + allowed + " allowed / " + denied + " denied";
        }

        return result;
    }
}

class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember() {
    }

    public LibraryMember(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class MembershipFieldReachChecker {

    public static void main(String[] args) {

        System.out.println(
                AccessChecker.classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessChecker.summarizeByModifier(attempts)
        );

        LibraryMember member = new LibraryMember(
                "MP1234",
                "VJA01",
                150.0,
                "Susaadhana"
        );

        System.out.println(member.displayName);
        System.out.println(member.branchCode);
        System.out.println(member.finesOwed);
    }
}