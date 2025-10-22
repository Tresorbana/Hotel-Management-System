import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("Welcome to Tresor Grand Hotel");

        while (isRunning) {
            System.out.println("========================");
            System.out.println("\nMenu:");
            System.out.println("1. Add room");
            System.out.println("2. Create customer");
            System.out.println("3. Make reservation");
            System.out.println("4. Show available rooms");
            System.out.println("5. Cancel reservation");
            System.out.println("6. Show reservations");
            System.out.println("Type q or exit to quit");

            String option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "1":
                    System.out.print("Room number: ");
                    int roomNumber = Integer.parseInt(scanner.nextLine());
                    System.out.print("Room type: ");
                    String roomType = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    hotel.addRoom(roomNumber, roomType, price);
                    break;

                case "2":
                    System.out.print("Customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    Customer customer = new Customer(name, email, phone);
                    hotel.addCustomer(customer);
                    break;

                case "3":
                    if (!hotel.hasCustomers()) {
                        System.out.println("No customers. Please add a customer first.");
                        break;
                    }
                    System.out.println("Customers:");
                    hotel.showCustomers();
                    System.out.print("Enter customer name: ");
                    String custName = scanner.nextLine();
                    System.out.println("Available rooms:");
                    hotel.showAvailableRooms();
                    System.out.print("Enter room number to reserve: ");
                    int reserveRoom = Integer.parseInt(scanner.nextLine());
                    hotel.makeReservation(custName, reserveRoom);
                    break;

                case "4":
                    hotel.showAvailableRooms();
                    break;

                case "5":
                    System.out.print("Customer name: ");
                    String cancelName = scanner.nextLine();
                    System.out.print("Room number: ");
                    int cancelRoom = Integer.parseInt(scanner.nextLine());
                    hotel.cancelReservation(cancelName, cancelRoom);
                    break;

                case "6":
                    hotel.viewReservations();
                    break;

                case "q":
                case "exit":
                    isRunning = false;
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Please enter a valid option.");
            }
        }
        scanner.close();
    }
}
