class person{
    String name;
    int id;
    void performduties(){
        System.out.println("Name: "+name);
        System.out.println("ID: "+id);
    
    }
}
interface worker{
    void performduties();
}
class waiter extends person implements worker{
    public void performduties(){
        super.performduties();
        System.out.println("Manager "+name+" is managing the restaurant.");
    }

}
class chef extends person implements worker{

    public void performduties(){
        super.performduties();
        System.out.println("Chef "+name+" is preparing food.");

    }
}
public class restaurantmanagement {
    public static void main(String[] args) {
        waiter w1=new waiter();
        w1.name="Nishant";
        w1.id=101;
        w1.performduties();
        System.out.println();
        chef c1=new chef();
        c1.name="bheem";
        c1.id=102;
        c1.performduties();
    }
}
