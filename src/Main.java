import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== HOTEL SYSTEM ======");
            System.out.println("1. Add Room");
            System.out.println("2. Add Customer");
            System.out.println("3. Make Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. View Customers");
            System.out.println("6. View Rooms");
            System.out.println("7. View Reservations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choiceInput = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number from 0 to 7.");
                continue;
            }

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter room number (integer): ");
                        int n = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter room type (Single, Double, Suite): ");
                        String t = sc.nextLine();

                        System.out.print("Enter room price (e.g., 120.50): ");
                        double p = Double.parseDouble(sc.nextLine());

                        hotel.addRoom(n, t, p);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid number. Please try again.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                case 2:
                    while (true) {
                        try {
                            System.out.print("Enter customer name: ");
                            String name = sc.nextLine();

                            System.out.print("Enter customer phone: ");
                            String phone = sc.nextLine();

                            System.out.print("Enter customer email: ");
                            String email = sc.nextLine();

                            hotel.addCustomer(name, phone, email);
                            break;

                        } catch (IllegalArgumentException ex) {
                            System.out.println("Error: " + ex.getMessage());
                            System.out.println("Please re-enter the customer information.\n");
                        }
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter customer name: ");
                        String cn = sc.nextLine();

                        System.out.print("Enter room number to reserve: ");
                        int rn = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        LocalDate s = LocalDate.parse(sc.nextLine());

                        System.out.print("Enter end date (YYYY-MM-DD): ");
                        LocalDate e = LocalDate.parse(sc.nextLine());

                        System.out.println("Processing reservation...");
                        hotel.makeReservation(cn, rn, s.toString(), e.toString());

                        Thread.sleep(300);
                        System.out.println("\n--- Reservation Status ---");
                        hotel.showReservations();

                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter customer name: ");
                        String c2 = sc.nextLine();

                        System.out.print("Enter room number to cancel: ");
                        int r2 = Integer.parseInt(sc.nextLine());

                        hotel.cancelReservation(c2, r2);

                        System.out.println("\n--- Current Reservations ---");
                        hotel.showReservations();
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\n--- Customer List ---");
                    hotel.showCustomers();
                    break;

                case 6:
                    System.out.println("\n--- Room List ---");
                    hotel.showRooms();
                    break;

                case 7:
                    System.out.println("\n--- Reservation List ---");
                    hotel.showReservations();
                    break;

                case 0:
                    System.out.println("Exiting the system...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number from 0 to 7.");
            }
        }
    }
}
