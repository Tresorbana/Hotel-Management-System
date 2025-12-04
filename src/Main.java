import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel h = new Hotel();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add Room");
            System.out.println("2 Add Customer");
            System.out.println("3 Make Reservation");
            System.out.println("4 Cancel Reservation");
            System.out.println("5 View Customers");
            System.out.println("6 View Rooms");
            System.out.println("7 View Reservations");
            System.out.println("0 Exit");
            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 1) {
                int n = Integer.parseInt(sc.nextLine());
                String t = sc.nextLine();
                double p = Double.parseDouble(sc.nextLine());
                h.addRoom(n, t, p);
            } else if (ch == 2) {
                String name = sc.nextLine();
                String phone = sc.nextLine();
                String email = sc.nextLine();
                h.addCustomer(name, phone, email);
            } else if (ch == 3) {
                String name = sc.nextLine();
                int rn = Integer.parseInt(sc.nextLine());
                String s = sc.nextLine();
                String e = sc.nextLine();
                h.makeReservation(name, rn, s, e);
            } else if (ch == 4) {
                String name = sc.nextLine();
                int rn = Integer.parseInt(sc.nextLine());
                h.cancelReservation(name, rn);
            } else if (ch == 5) {
                h.showCustomers();
            } else if (ch == 6) {
                h.showRooms();
            } else if (ch == 7) {
                h.showReservations();
            } else if (ch == 0) break;
        }
    }
}
