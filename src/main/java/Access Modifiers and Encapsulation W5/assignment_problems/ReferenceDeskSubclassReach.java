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

    static String firstDeniedAttempt(String[][] attempts) {
        for (int i = 0; i < attempts.length; i++) {
            if (classifyAccess(attempts[i][0], attempts[i][1]).equals("DENIED")) {
                return attempts[i][0] + " via " + attempts[i][1]
                        + " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }
}

class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class PremiumLibraryMember extends LibraryMember {

    public PremiumLibraryMember(String membershipPin, String branchCode,
                                double finesOwed, String displayName) {
        super(membershipPin, branchCode, finesOwed, displayName);
    }

    void showFine() {
        System.out.println(finesOwed);
    }
}

public class ReferenceDeskSubclassReach {

    public static void main(String[] args) {

        String[][] attempts = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
                AccessChecker.firstDeniedAttempt(attempts)
        );

        String[][] attempts2 = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"}
        };

        System.out.println(
                AccessChecker.firstDeniedAttempt(attempts2)
        );
    }
}