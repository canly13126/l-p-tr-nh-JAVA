
class Book {
    private String title;
    private String author;
    private double price;

    public Book() {
    }

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        setPrice(price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Giá sách không được âm!");
        }
    }

    public void display() {
        System.out.println("Tên sách: " + title);
        System.out.println("Tác giả: " + author);
        System.out.println("Giá sách: " + price);
    }
}

public class bai2ss7 {
    public static void main(String[] args) {
        Book book1 = new Book();

        book1.setTitle("Lập trình Java");
        book1.setAuthor("Nguyễn Văn A");
        book1.setPrice(150000);

        System.out.println("=== THÔNG TIN SÁCH 1 ===");
        book1.display();

        Book book2 = new Book("Java cơ bản", "Trần Văn B", 200000);

        System.out.println("\n=== THÔNG TIN SÁCH 2 ===");
        book2.display();

        System.out.println("\n=== KIỂM TRA GIÁ KHÔNG HỢP LỆ ===");
        book2.setPrice(-50000);
        System.out.println("Giá sách hiện tại: " + book2.getPrice());
    }
}
