import java.util.*;

interface WashType {
    int getDuration();
    double getCharge();
    String getName();
}

class QuickWash implements WashType {
    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }

    public String getName() {
        return "Quick";
    }
}

class NormalWash implements WashType {
    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }

    public String getName() {
        return "Normal";
    }
}

class HeavyWash implements WashType {
    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }

    public String getName() {
        return "Heavy";
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void display() {
        System.out.println(washType.getName() + " wash started on "
                + machine.getId() + " for " + student.getName()
                + " (" + washType.getDuration() + " min). Charge: ₹"
                + String.format("%.2f", washType.getCharge()) + ".");
    }
}

class WashingMachine {
    private String id;
    private WashCycle currentCycle;

    WashingMachine(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isFree() {
        return currentCycle == null;
    }

    public void startWash(Student student, WashType washType) {
        if (!isFree()) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        currentCycle = new WashCycle(student, this, washType);
        currentCycle.display();
    }

    public void completeCycle() {
        if (isFree()) {
            System.out.println(id + " is already free.");
            return;
        }

        System.out.println(id + " cycle completed.");
        currentCycle = null;
        System.out.println(id + " is now free.");
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        m1.completeCycle();
        m1.startWash(neha, new NormalWash());
    }
}