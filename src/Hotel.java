import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Customer> customers;
    private ArrayList<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(int roomNumber, String roomType, double price) {
        try {
            if (roomNumber <= 0 || price < 0 || roomType == null || roomType.isEmpty()) {
                throw new IllegalArgumentException("Invalid room details.");
            }
            rooms.add(new Room(roomNumber, roomType, price));
            System.out.println("Room added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding room: " + e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Input a number" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void addCustomer(Customer customer) {
        try {
            if (customer == null || customer.getName() == null || customer.getName().isEmpty()) {
                throw new IllegalArgumentException("Invalid customer details.");
            }
            customers.add(customer);
            System.out.println("Customer registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void showCustomers() {
        try {
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
                return;
            }
            for (int i = 0; i < customers.size(); i++) {
                System.out.println(i + ": " + customers.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error displaying customers: " + e.getMessage());
        }
    }

    public Customer getCustomer(int index) {
        try {
            if (index < 0 || index >= customers.size()) {
                throw new IndexOutOfBoundsException("Customer index out of range.");
            }
            return customers.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return null;
        }
    }

    public boolean hasCustomers() {
        return !customers.isEmpty();
    }

    public void showAvailableRooms() {
        try {
            boolean anyAvailable = false;
            for (Room room : rooms) {
                if (room.isAvailable()) {
                    System.out.println(room);
                    anyAvailable = true;
                }
            }
            if (!anyAvailable) {
                System.out.println("No rooms available.");
            }
        } catch (Exception e) {
            System.out.println("Error displaying rooms: " + e.getMessage());
        }
    }

    public void makeReservation(String customerName, int roomNumber) {
        try {
            if (customerName == null || customerName.isEmpty()) {
                throw new IllegalArgumentException("Invalid customer name.");
            }

            Customer chosenCustomer = null;
            for (Customer c : customers) {
                if (c.getName().equalsIgnoreCase(customerName)) {
                    chosenCustomer = c;
                    break;
                }
            }

            if (chosenCustomer == null) {
                System.out.println("Customer not found.");
                return;
            }

            Room chosenRoom = null;
            for (Room r : rooms) {
                if (r.getRoomNumber() == roomNumber && r.isAvailable()) {
                    chosenRoom = r;
                    break;
                }
            }

            if (chosenRoom == null) {
                System.out.println("Room not available.");
                return;
            }

            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            Reservation res = new Reservation(chosenCustomer, chosenRoom, today, tomorrow);
            reservations.add(res);
            System.out.println("Reservation created successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void cancelReservation(String customerName, int roomNumber) {
        try {
            Reservation toCancel = null;
            for (Reservation r : reservations) {
                if (r.getCustomer().getName().equalsIgnoreCase(customerName)
                        && r.getRoom().getRoomNumber() == roomNumber) {
                    toCancel = r;
                    break;
                }
            }

            if (toCancel != null) {
                toCancel.cancel();
                reservations.remove(toCancel);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Reservation not found.");
            }
        } catch (Exception e) {
            System.out.println("Error cancelling reservation: " + e.getMessage());
        }
    }

    public void viewReservations() {
        try {
            if (reservations.isEmpty()) {
                System.out.println("No reservations found.");
                return;
            }
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        } catch (Exception e) {
            System.out.println("Error displaying reservations: " + e.getMessage());
        }
    }
}
