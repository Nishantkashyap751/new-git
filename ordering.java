class order{
    int orderid;
    String orderdate;
    void orderstatus(){
        System.out.println("order id:"+orderid);
        System.out.println("order date:"+orderdate);
    }
}
class shippedorder extends order{
    int trackingnumber;
    void orderstatus(){
        super.orderstatus();
        System.out.println("tracking number:"+trackingnumber);
    }
}
class deliverorder extends shippedorder{
    String deliverdate;
    void orderstatus(){
        super.orderstatus();
        System.out.println("deliver date:"+deliverdate);
    }
}
public class ordering {
    public static void main(String ar[]){
        deliverorder d1=new deliverorder();
        deliverorder d2=new deliverorder();

        ((deliverorder)d1).orderid=1;
        ((deliverorder)d1).orderdate="21May2023";
        ((deliverorder)d1).trackingnumber=12345;
        ((deliverorder)d1).deliverdate="22May2023";
        d1.orderstatus();

        ((deliverorder)d2).orderid=2;
        ((deliverorder)d2).orderdate="22May2023";
        ((deliverorder)d2).trackingnumber=54321;
        ((deliverorder)d2).deliverdate="23May2023";
        d2.orderstatus();

    }
}
