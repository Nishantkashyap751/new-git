public class bankaccount {
    static String bankName="Central bank Of North Korea";
    String accountholdername;   
     final int Accountnumber;

    public static int getTotalAccounts(){
        return 100;
    }
    bankaccount(String accountholdername,int Accountnumber){
        this.Accountnumber=Accountnumber;
        this.accountholdername=accountholdername;
        if(this instanceof bankaccount) {
            this.accountholdername=accountholdername;
            System.out.println("Account holder name: "+accountholdername);
            System.out.println("Account number: "+Accountnumber);
            System.out.println("Bank name: "+bankName);
        } else {
            System.out.println("Object is not an instance of bankaccount. Details not displayed.");
        }
        
    }
    public static void main(String[]args){
        bankaccount b1=new bankaccount("kim-jo-un",12345);
        bankaccount b2=new bankaccount("kim-jong-il",67890);
    bankaccount.getTotalAccounts();
    System.out.println("Total accounts: "+getTotalAccounts());
    }
}