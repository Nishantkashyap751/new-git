class device {
    int deviceid;
    String status;
    void displaystatus(){
        System.out.println("device Id:"+deviceid);
        System.out.println("status:"+status);
    }
}
class thermostat extends device{
    String temperaturesetting;
    void displaystatus(){
        super.displaystatus();
        System.out.println("temperature setting:"+temperaturesetting);
    }
}
public class devices{
    public static void main(String ar[]){
        thermostat t1=new thermostat();
                thermostat t2=new thermostat();

        t1.deviceid=1;
        t1.status="on";
        t1.displaystatus();
        t2.deviceid=2;
        t2.status="off";
        t2.temperaturesetting="30";
        t2.displaystatus();
    
    }
}