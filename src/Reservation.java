import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private LocalDate bookIn;
    private LocalDate bookOut;
    private Customer customer;
    private Room room;

    public Reservation(Customer customer, Room room, LocalDate bookIn, LocalDate bookOut) {
        this.bookIn = bookIn;
        this.bookOut = bookOut;
        this.customer = customer;
        this.room = room;
        room.bookRoom();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getBookIn() {
        return bookIn;
    }

    public LocalDate getBookOut() {
        return bookOut;
    }

    public void cancel() {
        room.cancelBooking();
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customer.getName() + " | Room " + room.getRoomNumber() +
                " (" + room.getRoomType() + ") | From " + bookIn.format(fmt) +
                " to " + bookOut.format(fmt);
    }
}
