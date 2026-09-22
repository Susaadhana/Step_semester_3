class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty()
                || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.print(
                "Standard | Sessions: " + sessionsAttended
        );
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee,
                         String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.print(
                "Premium | Trainer: " + trainerName +
                        " | Sessions: " + sessionsAttended
        );
    }
}

public class MonthlyAttendance {

    static String batchPrint(GymMember[] members) {

        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {

            StringBuilder current = new StringBuilder();

            // Polymorphic call
            // Each object's own displayInfo() executes
            if (member instanceof PremiumMember) {
                PremiumMember p = (PremiumMember) member;

                current.append(
                        "Premium | Trainer: "
                );
                current.append(p.getTrainerName());
                current.append(
                        " | Sessions: "
                );
                current.append(
                        p.getSessionsAttended()
                );

                current.append(
                        " [Trainer via downcast: "
                );
                current.append(p.getTrainerName());
                current.append("]");

            } else {
                current.append(
                        "Standard | Sessions: "
                );
                current.append(
                        member.getSessionsAttended()
                );
            }

            result.append(current);
            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember[] members = {
                new GymMember("MEM6", 1000),
                new PremiumMember(
                        "MEM7",
                        2000,
                        "Coach Riya"
                )
        };

        System.out.println(
                batchPrint(members)
        );
    }
}