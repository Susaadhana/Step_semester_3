public class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty()
                || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    public int[] getFineHistory() {
        int[] result = new int[fineCount];

        for (int i = 0; i < fineCount; i++) {
            result[i] = fineHistory[i];
        }

        return result;
    }

    public int getTotalFine() {
        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}