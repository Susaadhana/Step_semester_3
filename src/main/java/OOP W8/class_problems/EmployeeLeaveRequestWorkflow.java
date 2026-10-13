import java.time.LocalDate;

abstract class Employee {
    private String name;

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canRequestLeave(long days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canRequestLeave(long days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canRequestLeave(long days) {
        return days > 0 && days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    public boolean canRequestLeave(long days) {
        return days > 0 && days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status = "Pending";

    LeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean submit() {
        if (endDate.isBefore(startDate)) {
            System.out.println("Invalid leave dates.");
            return false;
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;

        if (!employee.canRequestLeave(days)) {
            System.out.println("Leave request denied by employee leave policy.");
            return false;
        }

        System.out.println("Leave request submitted for " + employee.getName()
                + " (" + startDate + " to " + endDate + "). Status: " + status + ".");
        return true;
    }

    public void review(String reviewer, boolean approved) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot review: leave request is already " + status + ".");
            return;
        }

        status = approved ? "Approved" : "Rejected";
        System.out.println(employee.getName() + "'s leave request (" + startDate + " to "
                + endDate + ") " + status.toLowerCase() + " by " + reviewer
                + ". Status: " + status + ".");
    }

    public void changeStatus(String newStatus) {
        if (!status.equals("Pending") || !newStatus.equals("Pending")) {
            System.out.println("Cannot change leave request status from " + status
                    + " to " + newStatus + ".");
            return;
        }

        status = newStatus;
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        LeaveRequest johnRequest = new LeaveRequest(john,
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        johnRequest.submit();
        johnRequest.review("Alice", true);
        johnRequest.changeStatus("Pending");

        Employee jane = new PartTimeEmployee("Jane");
        LeaveRequest janeRequest = new LeaveRequest(jane,
                LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));
        janeRequest.submit();
        janeRequest.review("Bob", false);
    }
}
