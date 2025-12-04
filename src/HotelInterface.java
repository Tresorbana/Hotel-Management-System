public interface HotelInterface {
    void addRoom(int n, String t, double p);
    void addCustomer(String name, String phone, String email);
    void makeReservation(String name, int roomNumber, String start, String end);
    void cancelReservation(String name, int roomNumber);
}
