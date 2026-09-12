class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updatedSeats = seatNumbers.clone();
        updatedSeats[index] = newSeat;
        return new BookingReceipt(bookingId, updatedSeats);
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class ImmutableBookingReceiptNightlySettlement {

    static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullCount++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " + nullCount + " null\n"
                + "skipped | " + groupCount + " group | "
                + individualCount + " individual";
    }

    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt(
                "CH-1001",
                new String[]{"A1", "A2"}
        );

        String[] seats = b.getSeatNumbers();
        seats[0] = "X";

        System.out.println(b.getSeatNumbers()[0]);

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");

        System.out.println(java.util.Arrays.toString(b.getSeatNumbers()));
        System.out.println(java.util.Arrays.toString(updated.getSeatNumbers()));

        System.out.println(processNightlySettlement(new BookingReceipt[]{
                new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
                null,
                new BookingReceipt("CH-3003", new String[]{"C1"})
        }));
    }
}