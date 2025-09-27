abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public abstract double calculateInterest();
}

interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

class SavingsAccount extends BankAccount implements Loanable {
    private double loanAmount;

    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    public double calculateInterest() {
        return getBalance() * 0.04;
    }

    public void applyForLoan(double amount) {
        loanAmount = amount;
    }

    public boolean calculateLoanEligibility() {
        return getBalance() > 1000;
    }
}

class CurrentAccount extends BankAccount implements Loanable {
    private double loanAmount;

    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    public double calculateInterest() {
        return getBalance() * 0.02;
    }

    public void applyForLoan(double amount) {
        loanAmount = amount;
    }

    public boolean calculateLoanEligibility() {
        return getBalance() > 5000;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        BankAccount a1 = new SavingsAccount("S101", "Alice", 5000);
        BankAccount a2 = new CurrentAccount("C202", "Bob", 20000);

        BankAccount[] accounts = {a1, a2};

        for (BankAccount acc : accounts) {
            System.out.println(acc.getHolderName() + " Interest: " + acc.calculateInterest());
            if (acc instanceof Loanable) {
                Loanable l = (Loanable) acc;
                l.applyForLoan(10000);
                System.out.println("Loan Eligibility: " + l.calculateLoanEligibility());
            }
            System.out.println();
        }
    }
}
