import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable, Comparable<Reservation> {

    private Customer customer;
    private Room room;
    private LocalDate start;
    private LocalDate end;
    private boolean cancelled;

    public Reservation(Customer c, Room r, LocalDate s, LocalDate e) {
        customer = c;
        room = r;
        start = s;
        end = e;
        cancelled = false;
        r.setAvailable(false);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public synchronized void cancel() {
        cancelled = true;
        room.setAvailable(true);
    }

    @Override
    public int compareTo(Reservation o) {
        return start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return customer.getName() + " | Room " + room.getRoomNumber() +
                " | " + start + " → " + end + " | Cancelled=" + cancelled;
    }
}
