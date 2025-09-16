public class bankaccount {
    String accountHolder;
    int accountNumber;
    double balance;

    void get(String name, int accNo, double initialBalance) {
        accountHolder = name;
        accountNumber = accNo;
        balance = initialBalance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    void display() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args) {
        bankaccount account = new bankaccount();
        account.get("nishant Kashyap", 123456, 1000.0);
        account.display();
        account.deposit(500.0);
        account.withdraw(200.0);
        account.display();
    }
}
