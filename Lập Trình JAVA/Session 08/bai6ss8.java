
import java.util.Arrays;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int year;

    public Book(String title, String author, String isbn, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    public String getDetails() {
        return "Tiêu đề: " + title
                + "\nTác giả: " + author
                + "\nMã ISBN: " + isbn
                + "\nNăm xuất bản: " + year;
    }

    public String getIsbn() {
        return isbn;
    }
}

interface IBookManager {
    void addBook(Book book);

    void removeBook(String isbn);

    void displayBooks();
}

class BookManager implements IBookManager {
    private Book[] books = new Book[0];

    @Override
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                System.out.println("Mã ISBN đã tồn tại!");
                return;
            }
        }

        books = Arrays.copyOf(books, books.length + 1);
        books[books.length - 1] = book;

        System.out.println("Thêm sách thành công!");
    }

    @Override
    public void removeBook(String isbn) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                for (int j = i; j < books.length - 1; j++) {
                    books[j] = books[j + 1];
                }

                books = Arrays.copyOf(books, books.length - 1);
                System.out.println("Xóa sách thành công!");
                return;
            }
        }

        System.out.println("Không tìm thấy sách có mã ISBN: " + isbn);
    }

    @Override
    public void displayBooks() {
        if (books.length == 0) {
            System.out.println("Danh sách sách đang trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH SÁCH ==========");

        for (int i = 0; i < books.length; i++) {
            System.out.println("Sách thứ " + (i + 1) + ":");
            System.out.println(books[i].getDetails());
            System.out.println("-----------------------------------");
        }
    }
}

public class bai6ss8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager manager = new BookManager();

        while (true) {
            System.out.println("\n========== QUẢN LÝ SÁCH ==========");
            System.out.println("1. Thêm sách mới");
            System.out.println("2. Hiển thị danh sách sách");
            System.out.println("3. Xóa sách theo mã ISBN");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int luaChon;

            try {
                luaChon = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (luaChon) {
                case 1:
                    System.out.print("Nhập tiêu đề sách: ");
                    String title = scanner.nextLine();

                    System.out.print("Nhập tác giả: ");
                    String author = scanner.nextLine();

                    System.out.print("Nhập mã ISBN: ");
                    String isbn = scanner.nextLine();

                    try {
                        System.out.print("Nhập năm xuất bản: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());

                        if (year <= 0) {
                            System.out.println("Năm xuất bản không hợp lệ!");
                            break;
                        }

                        Book book = new Book(title, author, isbn, year);
                        manager.addBook(book);

                    } catch (NumberFormatException e) {
                        System.out.println("Năm xuất bản không hợp lệ!");
                    }
                    break;

                case 2:
                    manager.displayBooks();
                    break;

                case 3:
                    System.out.print("Nhập mã ISBN cần xóa: ");
                    String isbnXoa = scanner.nextLine();

                    manager.removeBook(isbnXoa);
                    break;

                case 4:
                    System.out.println("Kết thúc chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
