import java.io.Serializable;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Customer implements Serializable, Comparable<Customer>, Comparator<Customer> {
    private String name;
    private String phone;
    private String email;

    public Customer() {}

    public Customer(String name, String phone, String email) {
        if (!Pattern.compile("^[A-Za-z ]+$").matcher(name).matches())
            throw new IllegalArgumentException("Invalid name");
        if (!Pattern.compile("^07\\d{8}$").matcher(phone).matches())
            throw new IllegalArgumentException("Invalid phone");
        if (!Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$").matcher(email).matches())
            throw new IllegalArgumentException("Invalid email");
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Customer o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int compare(Customer a, Customer b) {
        return a.name.compareToIgnoreCase(b.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode() + phone.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return name.equalsIgnoreCase(c.name) && phone.equals(c.phone);
    }

    @Override
    public String toString() {
        return name + " | " + phone + " | " + email;
    }
}
