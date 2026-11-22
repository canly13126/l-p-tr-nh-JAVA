import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ SÁCH TRONG THƯ VIỆN =====");
            System.out.println("1. Thêm sách mới");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách theo tác giả");
            System.out.println("5. Hiển thị danh sách tất cả sách");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 6!");
                continue;
            }

            switch (choice) {
                case 1:
                    String title = inputNonEmptyString(scanner, "Nhập tên sách: ", "Tên sách");
                    String author = inputNonEmptyString(scanner, "Nhập tên tác giả: ", "Tên tác giả");
                    int year = inputPublishedYear(scanner);
                    double price = inputPrice(scanner);
                    bookManager.addBook(new Book(title, author, year, price));
                    break;

                case 2:
                    int updateId = inputPositiveInt(scanner, "Nhập ID sách cần cập nhật: ", "ID sách");
                    String newTitle = inputNonEmptyString(scanner, "Nhập tên sách mới: ", "Tên sách");
                    String newAuthor = inputNonEmptyString(scanner, "Nhập tên tác giả mới: ", "Tên tác giả");
                    int newYear = inputPublishedYear(scanner);
                    double newPrice = inputPrice(scanner);
                    bookManager.updateBook(updateId, new Book(newTitle, newAuthor, newYear, newPrice));
                    break;

                case 3:
                    int deleteId = inputPositiveInt(scanner, "Nhập ID sách cần xóa: ", "ID sách");
                    bookManager.deleteBook(deleteId);
                    break;

                case 4:
                    String searchAuthor = inputNonEmptyString(scanner, "Nhập tên tác giả cần tìm: ", "Tên tác giả");
                    bookManager.findBooksByAuthor(searchAuthor);
                    break;

                case 5:
                    bookManager.listAllBooks();
                    break;

                case 6:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 6!");
            }
        }
    }

    private static String inputNonEmptyString(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            try {
                System.out.print(prompt);
                String value = scanner.nextLine().trim();
                if (value.isEmpty()) {
                    throw new IllegalArgumentException(fieldName + " không được để trống!");
                }
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static int inputPositiveInt(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException(fieldName + " không được để trống!");
                }
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    throw new IllegalArgumentException(fieldName + " phải là số nguyên dương!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: " + fieldName + " phải là một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static int inputPublishedYear(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập năm xuất bản (1901 - 2155): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Năm xuất bản không được để trống!");
                }
                int year = Integer.parseInt(input);
                if (year < 1901 || year > 2155) {
                    throw new IllegalArgumentException("Năm xuất bản phải nằm trong khoảng từ 1901 đến 2155!");
                }
                return year;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Năm xuất bản phải là một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static double inputPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập giá sách (>= 0): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Giá sách không được để trống!");
                }
                double price = Double.parseDouble(input);
                if (price < 0) {
                    throw new IllegalArgumentException("Giá sách không được là số âm!");
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Giá sách phải là một số thực hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }
}
