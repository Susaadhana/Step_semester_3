import java.util.*;

interface Seat {
    String getId();
    double getPrice();
}

class RegularSeat implements Seat {
    private String id;

    RegularSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat implements Seat {
    private String id;

    PremiumSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat implements Seat {
    private String id;

    ReclinerSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {
    private String time;
    private boolean started;
    private Map<String, Seat> bookedSeats = new HashMap<>();

    Show(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public boolean isStarted() {
        return started;
    }

    public void startShow() {
        started = true;
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeats.containsKey(seat.getId());
    }

    public boolean bookSeat(Seat seat) {
        if (!isAvailable(seat)) {
            return false;
        }

        bookedSeats.put(seat.getId(), seat);
        return true;
    }

    public void releaseSeat(Seat seat) {
        bookedSeats.remove(seat.getId());
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats = new ArrayList<>();
    private boolean cancelled;

    Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
    }

    public boolean addSeats(Seat... selectedSeats) {
        if (cancelled || show.isStarted()) {
            System.out.println("Booking is not allowed.");
            return false;
        }

        if (selectedSeats.length == 0 || selectedSeats.length > 6) {
            System.out.println("You can book between 1 and 6 seats.");
            return false;
        }

        Set<String> ids = new HashSet<>();

        for (Seat seat : selectedSeats) {
            if (!ids.add(seat.getId()) || !show.isAvailable(seat)) {
                System.out.println("Seat " + seat.getId()
                        + " is already booked for this show.");
                return false;
            }
        }

        for (Seat seat : selectedSeats) {
            show.bookSeat(seat);
            seats.add(seat);
        }

        displayBooking();
        return true;
    }

    private void displayBooking() {
        System.out.print("Booking confirmed for " + customer.getName() + ": ");

        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).getId());

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(". Total: ₹%.2f.%n", getTotal());
    }

    public double getTotal() {
        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {
        if (cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (show.isStarted()) {
            System.out.println("Cannot cancel after the show starts.");
            return;
        }

        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        cancelled = true;

        System.out.print(customer.getName() + "'s booking cancelled. Seats ");

        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).getId());

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" released.");
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show);
        b1.addSeats(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
        );

        Booking b2 = new Booking(ravi, show);
        b2.addSeats(new RegularSeat("A2"));
        b2.addSeats(new ReclinerSeat("R1"));

        b1.cancel();

        Booking b3 = new Booking(neha, show);
        b3.addSeats(new RegularSeat("A2"));
    }
}