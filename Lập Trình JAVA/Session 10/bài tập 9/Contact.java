import java.util.Objects;

public class Contact {
    private static int autoId = 1;
    private int id;
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.id = autoId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static Contact forSearch(String phoneNumber) {
        Contact dummy = new Contact();
        dummy.phoneNumber = phoneNumber;
        return dummy;
    }

    private Contact() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Tên: %-22s | SĐT: %s", id, name, phoneNumber);
    }
}
