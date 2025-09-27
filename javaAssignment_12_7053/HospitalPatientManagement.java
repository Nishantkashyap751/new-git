abstract class Patient {
    private int patientId;
    private String name;
    private int age;

    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double calculateBill();

    public void getPatientDetails() {
        System.out.println("ID: " + patientId + ", Name: " + name + ", Age: " + age);
    }
}

interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

class InPatient extends Patient implements MedicalRecord {
    private double dailyRate;
    private int daysAdmitted;
    private String medicalHistory = "";

    public InPatient(int patientId, String name, int age, double dailyRate, int daysAdmitted) {
        super(patientId, name, age);
        this.dailyRate = dailyRate;
        this.daysAdmitted = daysAdmitted;
    }

    public double calculateBill() {
        return dailyRate * daysAdmitted;
    }

    public void addRecord(String record) {
        medicalHistory += record + "\n";
    }

    public void viewRecords() {
        System.out.println("Medical Records:\n" + medicalHistory);
    }
}

class OutPatient extends Patient implements MedicalRecord {
    private double consultationFee;
    private String medicalHistory = "";

    public OutPatient(int patientId, String name, int age, double consultationFee) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
    }

    public double calculateBill() {
        return consultationFee;
    }

    public void addRecord(String record) {
        medicalHistory += record + "\n";
    }

    public void viewRecords() {
        System.out.println("Medical Records:\n" + medicalHistory);
    }
}

public class HospitalPatientManagement {
    public static void main(String[] args) {
        Patient p1 = new InPatient(1, "Alice", 30, 2000, 5);
        Patient p2 = new OutPatient(2, "Bob", 25, 500);

        MedicalRecord m1 = (MedicalRecord) p1;
        MedicalRecord m2 = (MedicalRecord) p2;

        m1.addRecord("Blood Test Done");
        m2.addRecord("X-Ray Done");

        Patient[] patients = {p1, p2};

        for (Patient p : patients) {
            p.getPatientDetails();
            System.out.println("Bill: " + p.calculateBill());
            ((MedicalRecord)p).viewRecords();
            System.out.println();
        }
    }
}
