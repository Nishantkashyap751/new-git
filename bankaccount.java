public class bankaccount{
    protected String accountHolderName;
    protected String accountNumber;
    private double balance;
     public void balanceupdate(double balance){
        this.balance=balance;
        System.out.println("Balance: " + balance);
}}
class savingsAccount extends bankaccount{
    public void setDetails(String accountHolderName,String accountNumber){
        this.accountHolderName=accountHolderName;
        this.accountNumber=accountNumber;
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
    }
    public static void main(String ar[]){
        savingsAccount sa=new savingsAccount();
        sa.setDetails("Nishant","12345678");
        sa.balanceupdate(7000);
    }
}
