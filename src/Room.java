public class Room {
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double price) {
        if (roomNumber <= 0 || price < 0 || roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Invalid room details.");
        }
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        try {
            if (!isAvailable) {
                throw new IllegalStateException("Room " + roomNumber + " is already booked.");
            }
            isAvailable = false;
            System.out.println("Room " + roomNumber + " has been booked.");
        } catch (IllegalStateException e) {
            System.out.println("Booking error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void cancelBooking() {
        try {
            if (isAvailable) {
                throw new IllegalStateException("Room " + roomNumber + " is already available.");
            }
            isAvailable = true;
            System.out.println("Booking for room " + roomNumber + " has been canceled.");
        } catch (IllegalStateException e) {
            System.out.println("Cancellation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - $" + price + " - " +
                (isAvailable ? "Available" : "Booked");
    }

    public String getRoomDetails() {
        return toString();
    }
}

class DeluxeRoom extends Room {
    private boolean hasMiniBar;

    public DeluxeRoom(int roomNumber, double price, boolean hasMiniBar) {
        super(roomNumber, "Deluxe", price);
        this.hasMiniBar = hasMiniBar;
    }

    @Override
    public String getRoomDetails() {
        return super.toString() + (hasMiniBar ? " + Mini Bar" : "");
    }
}

class SuiteRoom extends Room {
    private boolean hasJacuzzi;

    public SuiteRoom(int roomNumber, double price, boolean hasJacuzzi) {
        super(roomNumber, "Suite", price);
        this.hasJacuzzi = hasJacuzzi;
    }

    @Override
    public String getRoomDetails() {
        return super.toString() + (hasJacuzzi ? " + Jacuzzi" : "");
    }
}
