class FeeAccount {
    String type;

    FeeAccount(String type) {
        this.type = type;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String type) {
        super(type);
    }
}

public class AccountBatchPayments {

    int hostelCount = 0;
    int dayScholarCount = 0;

    void processPayment(FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {

            System.out.println(
                    "Paid in two installments (hostel account)");

            hostelCount++;

        } else {

            System.out.println(
                    "Paid in one go (day-scholar account)");

            dayScholarCount++;
        }
    }

    public static void main(String[] args) {

        FeeAccount[] accounts = {
                new HostelFeeAccount("Hostel"),
                new HostelFeeAccount("Hostel"),
                new FeeAccount("FeeAccount"),
                new FeeAccount("FeeAccount")
        };

        AccountBatchPayments batch =
                new AccountBatchPayments();

        for (FeeAccount account : accounts) {
            batch.processPayment(account, 60000);
        }

        System.out.println(
                "Hostel accounts processed: "
                        + batch.hostelCount
                        + " | Day-scholar accounts processed: "
                        + batch.dayScholarCount);
    }
}