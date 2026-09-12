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
                    accessorContext.equals("SAME_PACKAGE") ||
                    accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";
            else
                return "DENIED";
        } else if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
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

class PremiumMovieTicket extends MovieTicket {

    PremiumMovieTicket(int seatNumber, String screenId, double ticketPrice, String movieTitle) {
        super(seatNumber, screenId, ticketPrice, movieTitle);
    }

    void showPrice() {
        System.out.println(ticketPrice);
    }
}

public class SubclassTicketAccess {

    public static void main(String[] args) {

        System.out.println(
                AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );

        System.out.println(
                AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")
        );

        System.out.println(
                AccessChecker.classifyAccess("private", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );

        System.out.println(
                AccessChecker.classifyAccess("default", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );

        System.out.println(
                AccessChecker.classifyAccess("public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")
        );

        PremiumMovieTicket ticket = new PremiumMovieTicket(
                25,
                "SCREEN-03",
                250.0,
                "Avengers"
        );

        ticket.showPrice();
    }
}