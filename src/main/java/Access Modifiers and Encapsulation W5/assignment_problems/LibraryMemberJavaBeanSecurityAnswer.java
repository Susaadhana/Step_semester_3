class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premium;
    private String securityAnswer;

    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        if (this.membershipId == null) {
            this.membershipId = membershipId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswer = Integer.toHexString(answer.hashCode());
        }
    }
}

public class LibraryMemberJavaBeanSecurityAnswer {
    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Riya");
        m.setPremium(true);
        m.setMembershipId("FAKE-0000");
        m.setSecurityAnswer("BlueMountain");

        System.out.println(m.getMembershipId());
        System.out.println(m.getName());
        System.out.println(m.isPremium());
    }
}