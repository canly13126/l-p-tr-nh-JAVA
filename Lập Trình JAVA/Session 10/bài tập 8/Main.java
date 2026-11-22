import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Book> bookSet = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ BỘ SƯU TẬP SÁCH =====");
            System.out.println("1. Thêm sách vào bộ sưu tập");
            System.out.println("2. Hiển thị toàn bộ bộ sưu tập sách");
            System.out.println("3. Lọc sách xuất bản sau năm chỉ định và sắp xếp theo tên");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng (1-4): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 4!");
                continue;
            }

            switch (choice) {
                case 1:
                    addBook(scanner, bookSet);
                    break;
                case 2:
                    displayAllBooks(bookSet);
                    break;
                case 3:
                    filterAndSortBooks(scanner, bookSet);
                    break;
                case 4:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void addBook(Scanner scanner, Set<Book> bookSet) {
        String id;
        while (true) {
            System.out.print("Nhập mã sách: ");
            id = scanner.nextLine().trim();
            if (!id.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Mã sách không được để trống!");
        }

        String title;
        while (true) {
            System.out.print("Nhập tên sách: ");
            title = scanner.nextLine().trim();
            if (!title.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Tên sách không được để trống!");
        }

        String author;
        while (true) {
            System.out.print("Nhập tên tác giả: ");
            author = scanner.nextLine().trim();
            if (!author.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Tên tác giả không được để trống!");
        }

        int year;
        while (true) {
            try {
                System.out.print("Nhập năm xuất bản: ");
                year = Integer.parseInt(scanner.nextLine());
                if (year <= 0) {
                    System.out.println("❌ Lỗi: Năm xuất bản phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Năm xuất bản phải là một số nguyên hợp lệ!");
            }
        }

        Book newBook = new Book(id, title, author, year);
        if (bookSet.add(newBook)) {
            System.out.println("✅ Thêm sách vào bộ sưu tập thành công!");
        } else {
            System.out.println("❌ Lỗi: Mã sách \"" + id + "\" đã tồn tại trong bộ sưu tập!");
        }
    }

    private static void displayAllBooks(Set<Book> bookSet) {
        if (bookSet.isEmpty()) {
            System.out.println("⚠️ Bộ sưu tập sách hiện đang trống.");
            return;
        }
        System.out.println("\n--- TOÀN BỘ BỘ SƯU TẬP SÁCH ---");
        for (Book book : bookSet) {
            System.out.println(book);
        }
    }

    private static void filterAndSortBooks(Scanner scanner, Set<Book> bookSet) {
        if (bookSet.isEmpty()) {
            System.out.println("⚠️ Bộ sưu tập sách hiện đang trống.");
            return;
        }

        int targetYear;
        while (true) {
            try {
                System.out.print("Nhập mốc năm xuất bản cần lọc (lấy sách xuất bản sau năm này): ");
                targetYear = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập một năm hợp lệ!");
            }
        }

        List<Book> filteredList = new ArrayList<>();
        for (Book book : bookSet) {
            if (book.getYear() > targetYear) {
                filteredList.add(book);
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("⚠️ Không có cuốn sách nào xuất bản sau năm " + targetYear + ".");
            return;
        }

        filteredList.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));

        System.out.println("\n--- DANH SÁCH SÁCH XUẤT BẢN SAU NĂM " + targetYear + " (ĐÃ SẮP XẾP THEO TÊN) ---");
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.println((i + 1) + ". " + filteredList.get(i));
        }
    }
}
