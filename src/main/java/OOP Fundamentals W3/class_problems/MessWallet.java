class MessWallet {
    private double balance;

    MessWallet(double balance) {
        if (balance < 0) {
            System.out.println("Invalid balance. Starting with 0.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid top-up amount");
        } else {
            balance += amount;
            System.out.println("Balance after top-up: " + balance);
        }
    }

    void deduct(double amount) {
        if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
        } else {
            balance -= amount;
        }
    }

    double getBalance() {
        return balance;
    }

    public static void main(String[] args) {

        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);
        wallet.deduct(1000);

        System.out.println("Final balance: " + wallet.getBalance());
    }
}