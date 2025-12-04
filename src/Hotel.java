import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hotel implements HotelInterface {
    private ArrayList<Room> rooms;
    private ArrayList<Customer> customers;
    private ArrayList<Reservation> reservations;

    private final String RF = "rooms.dat";
    private final String CF = "customers.dat";
    private final String RS = "reservations.dat";

    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public Hotel() {
        rooms = DataStore.load(RF);
        customers = DataStore.load(CF);
        reservations = DataStore.load(RS);
        Thread auto = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(8000);
                    saveAll();
                } catch (Exception ignored) {}
            }
        });
        auto.setDaemon(true);
        auto.start();
    }

    private synchronized void saveAll() {
        DataStore.save(RF, rooms);
        DataStore.save(CF, customers);
        DataStore.save(RS, reservations);
    }

    @Override
    public synchronized void addRoom(int n, String t, double p) {
        rooms.add(new Room(n, t, p));
        Collections.sort(rooms);
        notifyAll();
        saveAll();
    }

    @Override
    public synchronized void addCustomer(String name, String phone, String email) {
        customers.add(new Customer(name, phone, email));
        Collections.sort(customers, new Customer());
        notifyAll();
        saveAll();
    }

    @Override
    public void makeReservation(String name, int roomNumber, String s, String e) {
        executor.submit(() -> process(name, roomNumber, LocalDate.parse(s), LocalDate.parse(e)));
    }

    private synchronized void process(String name, int roomNumber, LocalDate s, LocalDate e) {
        Customer c = customers.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (c == null) return;
        Room r = rooms.stream().filter(x -> x.getRoomNumber() == roomNumber && x.isAvailable()).findFirst().orElse(null);
        if (r == null) return;
        reservations.add(new Reservation(c, r, s, e));
        Collections.sort(reservations);
        saveAll();
    }

    @Override
    public synchronized void cancelReservation(String name, int roomNumber) {
        Reservation target = reservations.stream().filter(x -> x.getCustomer().getName().equalsIgnoreCase(name) && x.getRoom().getRoomNumber() == roomNumber).findFirst().orElse(null);
        if (target == null) return;
        target.cancel();
        saveAll();
    }

    public synchronized void showCustomers() {
        customers.forEach(System.out::println);
    }

    public synchronized void showRooms() {
        rooms.forEach(System.out::println);
    }

    public synchronized void showReservations() {
        reservations.forEach(System.out::println);
    }
}
