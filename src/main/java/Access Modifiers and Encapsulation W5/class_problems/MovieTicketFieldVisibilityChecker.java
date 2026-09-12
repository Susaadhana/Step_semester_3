class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        } else if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        } else if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        } else if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            String result = classifyAccess(attempt[0], attempt[1]);

            if (result.equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

class MovieTicket {
    private int seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    MovieTicket(int seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

public class MovieTicketFieldVisibilityChecker {

    public static void main(String[] args) {
        System.out.println(
                AccessChecker.classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessChecker.summarizeBatch(attempts)
        );

        MovieTicket ticket = new MovieTicket(
                25,
                "SCREEN-03",
                250.0,
                "Avengers"
        );

        System.out.println("Movie: " + ticket.movieTitle);
        System.out.println("Screen: " + ticket.screenId);
        System.out.println("Price: " + ticket.ticketPrice);
    }
}
