class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updatedBookIds = bookIds.clone();
        updatedBookIds[index] = newId;
        return new LoanReceipt(memberId, updatedBookIds);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

class CirculationLedger {
    private static String branchCode;

    static {
        branchCode = "MAIN-BRANCH";
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt)
                referenceOnly++;
            else
                regular++;
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | " + regular + " regular";
    }
}

public class ImmutableLoanReceiptNightlyCirculation {

    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");

        System.out.println(java.util.Arrays.toString(r.getBookIds()));
        System.out.println(java.util.Arrays.toString(corrected.getBookIds()));

        System.out.println(
                CirculationLedger.processNightlyCirculation(new LoanReceipt[]{
                        new ReferenceOnlyLoanReceipt(
                                "LIB-001",
                                new String[]{"BK-201"},
                                "Reading Room 3"
                        ),
                        null,
                        new LoanReceipt(
                                "LIB-002",
                                new String[]{"BK-201"}
                        )
                })
        );
    }
}