import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManagement movieManagement = new MovieManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ PHIM =====");
            System.out.println("1. Thêm phim");
            System.out.println("2. Liệt kê phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 5!");
                continue;
            }

            switch (choice) {
                case 1:
                    String title = inputNonEmptyString(scanner, "Nhập tiêu đề phim: ", "Tiêu đề phim");
                    String director = inputNonEmptyString(scanner, "Nhập tên đạo diễn: ", "Tên đạo diễn");
                    int year = inputPositiveInt(scanner, "Nhập năm phát hành: ", "Năm phát hành");
                    movieManagement.addMovie(title, director, year);
                    break;

                case 2:
                    movieManagement.listMovies();
                    break;

                case 3:
                    int updateId = inputPositiveInt(scanner, "Nhập ID phim cần sửa: ", "ID phim");
                    String newTitle = inputNonEmptyString(scanner, "Nhập tiêu đề phim mới: ", "Tiêu đề phim");
                    String newDirector = inputNonEmptyString(scanner, "Nhập tên đạo diễn mới: ", "Tên đạo diễn");
                    int newYear = inputPositiveInt(scanner, "Nhập năm phát hành mới: ", "Năm phát hành");
                    movieManagement.updateMovie(updateId, newTitle, newDirector, newYear);
                    break;

                case 4:
                    int deleteId = inputPositiveInt(scanner, "Nhập ID phim cần xóa: ", "ID phim");
                    movieManagement.deleteMovie(deleteId);
                    break;

                case 5:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
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
}
