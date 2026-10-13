import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private String id;
    private double pricePerNight;
    private List<Reservation> reservations = new ArrayList<>();

    Room(String id, double pricePerNight) {
        this.id = id;
        this.pricePerNight = pricePerNight;
    }

    public String getId() {
        return id;
    }

    public double calculatePrice(long nights) {
        return pricePerNight * nights;
    }

    public boolean isAvailable(LocalDate start, LocalDate end) {
        for (Reservation reservation : reservations) {
            if (reservation.isActive()
                    && start.isBefore(reservation.getEndDate())
                    && end.isAfter(reservation.getStartDate())) {
                return false;
            }
        }
        return true;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }
}

class StandardRoom extends Room {
    StandardRoom(String id) {
        super(id, 100);
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String id) {
        super(id, 180);
    }
}

class Suite extends Room {
    Suite(String id) {
        super(id, 300);
    }
}

class HotelCustomer {
    private String name;

    HotelCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private HotelCustomer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean active = true;

    Reservation(HotelCustomer customer, Room room, LocalDate startDate, LocalDate endDate,
                LocalDate cancellationDeadline) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel(LocalDate cancellationDate) {
        if (!active) {
            System.out.println("Reservation is already cancelled.");
            return;
        }

        if (!cancellationDate.isBefore(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed.");
            return;
        }

        active = false;
        room.removeReservation(this);
        System.out.println("Reservation for " + customer.getName() + ", "
                + room.getId() + " (" + startDate + " to " + endDate
                + ") cancelled successfully.");
    }
}

class Hotel {
    public Reservation reserve(HotelCustomer customer, Room room, LocalDate start,
                               LocalDate end, LocalDate cancellationDeadline) {
        if (!start.isBefore(end)) {
            System.out.println("Invalid reservation dates.");
            return null;
        }

        if (!room.isAvailable(start, end)) {
            System.out.println(room.getId() + " is not available from "
                    + start + " to " + end + ".");
            return null;
        }

        Reservation reservation = new Reservation(customer, room, start, end,
                cancellationDeadline);
        room.addReservation(reservation);

        long nights = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        System.out.println("Reservation confirmed for " + customer.getName()
                + ", " + room.getId() + " (" + start + " to " + end + ").");
        System.out.printf("Price: $%.2f.%n", room.calculatePrice(nights));
        return reservation;
    }

    public void checkAvailability(Room room, LocalDate start, LocalDate end) {
        if (room.isAvailable(start, end)) {
            System.out.println(room.getId() + " is available from "
                    + start + " to " + end + ".");
        } else {
            System.out.println(room.getId() + " is not available from "
                    + start + " to " + end + ".");
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        HotelCustomer a = new HotelCustomer("Customer A");
        HotelCustomer b = new HotelCustomer("Customer B");
        HotelCustomer c = new HotelCustomer("Customer C");

        Room standard = new StandardRoom("Standard Room 101");
        Room deluxe = new DeluxeRoom("Deluxe Room 201");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);
        LocalDate jan3 = LocalDate.of(2026, 1, 3);
        LocalDate jan7 = LocalDate.of(2026, 1, 7);

        hotel.checkAvailability(standard, jan1, jan5);

        Reservation r1 = hotel.reserve(a, standard, jan1, jan5,
                LocalDate.of(2025, 12, 31));

        hotel.reserve(b, standard, jan3, jan7,
                LocalDate.of(2026, 1, 1));

        if (r1 != null) {
            r1.cancel(LocalDate.of(2025, 12, 20));
        }

        hotel.reserve(c, deluxe, LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12), LocalDate.of(2026, 2, 9));
    }
}
