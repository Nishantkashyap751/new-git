class bankaccount{
    int accountnumber;
    double balance;
    void displayaccounttype(){
        System.out.println("Account Number: "+accountnumber);
        System.out.println("Balance: "+balance);
    
    }
}
class savingsaccount extends bankaccount{
    double interestrate;
    void displayaccounttype(){
        super.displayaccounttype();
        System.out.println("Interest Rate: "+interestrate);
    }
}
class checkingaccount extends bankaccount{
    int withdrawllimit;
    void displayaccounttype(){
        super.displayaccounttype();
        System.out.println("Withdrawl Limit: "+withdrawllimit);
    }
}

public class bankaccounttype {
    public static void main(String[] args) {
        checkingaccount c1=new checkingaccount();
        c1.accountnumber=12345;
        c1.balance=5000;
        c1.withdrawllimit=1000;
        c1.displayaccounttype();
        System.out.println();
    }
}
