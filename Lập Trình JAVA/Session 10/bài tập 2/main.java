import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Book> bookMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ DANH SÁCH SÁCH =====");
            System.out.println("1. Thêm sách");
            System.out.println("2. Tìm kiếm sách theo tên");
            System.out.println("3. Hiển thị danh sách sách");
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
                    addBook(scanner, bookMap);
                    break;
                case 2:
                    searchBook(scanner, bookMap);
                    break;
                case 3:
                    displayBooks(bookMap);
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

    private static void addBook(Scanner scanner, HashMap<String, Book> bookMap) {
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

        int publishYear;
        while (true) {
            try {
                System.out.print("Nhập năm xuất bản: ");
                publishYear = Integer.parseInt(scanner.nextLine());
                if (publishYear <= 0) {
                    System.out.println("❌ Lỗi: Năm xuất bản phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Năm xuất bản phải là một số nguyên hợp lệ!");
            }
        }

        bookMap.put(title.toLowerCase(), new Book(title, author, publishYear));
        System.out.println("✅ Thêm sách thành công!");
    }

    private static void searchBook(Scanner scanner, HashMap<String, Book> bookMap) {
        if (bookMap.isEmpty()) {
            System.out.println("⚠️ Danh sách sách hiện đang trống.");
            return;
        }

        System.out.print("Nhập tên sách cần tìm: ");
        String searchTitle = scanner.nextLine().trim();

        Book foundBook = bookMap.get(searchTitle.toLowerCase());
        if (foundBook != null) {
            System.out.println("✅ Tìm thấy sách:");
            System.out.println(foundBook);
        } else {
            System.out.println("❌ Không tìm thấy cuốn sách nào có tên \"" + searchTitle + "\".");
        }
    }

    private static void displayBooks(HashMap<String, Book> bookMap) {
        if (bookMap.isEmpty()) {
            System.out.println("⚠️ Danh sách sách hiện đang trống.");
            return;
        }

        System.out.println("\n--- DANH SÁCH SÁCH ---");
        int index = 1;
        for (Book book : bookMap.values()) {
            System.out.println(index + ". " + book);
            index++;
        }
    }
}
