import java.io.Serializable;

public class Room implements Serializable, Comparable<Room> {
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean available = true;

    public Room(int n, String t, double p) {
        roomNumber = n;
        roomType = t;
        price = p;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public synchronized void setAvailable(boolean a) {
        available = a;
    }

    @Override
    public int compareTo(Room o) {
        return Integer.compare(roomNumber, o.roomNumber);
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + " $" + price + " Available=" + available + ")";
    }
}
