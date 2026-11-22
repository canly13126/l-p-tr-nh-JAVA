import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Hiển thị danh sách sinh viên.");
            System.out.println("2. Thêm mới sinh viên.");
            System.out.println("3. Sửa sinh viên.");
            System.out.println("4. Xóa sinh viên.");
            System.out.println("5. Tìm kiếm sinh viên.");
            System.out.println("6. Thoát.");
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
                    displayAllStudents();
                    break;
                case 2:
                    addNewStudent(scanner);
                    break;
                case 3:
                case 4:
                case 5:
                    System.out.println("ℹ️ Chức năng này sẽ được thực hiện ở các bài tập tiếp theo!");
                    break;
                case 6:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void displayAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "{call get_all_students()}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String fullName = rs.getString("full_name");
                Date dob = rs.getDate("date_of_birth");
                String email = rs.getString("email");
                students.add(new Student(id, fullName, dob, email));
            }

            if (students.isEmpty()) {
                System.out.println("⚠️ Danh sách sinh viên trong cơ sở dữ liệu hiện đang trống.");
            } else {
                System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
                for (Student s : students) {
                    System.out.println(s);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối hoặc truy vấn cơ sở dữ liệu: " + e.getMessage());
        }
    }

    private static void addNewStudent(Scanner scanner) {
        String fullName;
        while (true) {
            System.out.print("Nhập họ và tên sinh viên: ");
            fullName = scanner.nextLine().trim();
            if (!fullName.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Họ và tên không được để trống!");
        }

        Date sqlDate;
        while (true) {
            try {
                System.out.print("Nhập ngày sinh (yyyy-MM-dd, ví dụ 2004-05-20): ");
                String dobInput = scanner.nextLine().trim();
                if (dobInput.isEmpty()) {
                    System.out.println("❌ Lỗi: Ngày sinh không được để trống!");
                    continue;
                }
                LocalDate localDate = LocalDate.parse(dobInput);
                sqlDate = Date.valueOf(localDate);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Định dạng ngày sinh không hợp lệ! Vui lòng nhập đúng dạng yyyy-MM-dd.");
            }
        }

        String email;
        while (true) {
            System.out.print("Nhập email sinh viên: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("❌ Lỗi: Email không được để trống!");
                continue;
            }
            if (!email.contains("@")) {
                System.out.println("❌ Lỗi: Email không đúng định dạng!");
                continue;
            }
            break;
        }

        String sql = "{call add_student(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, fullName);
            cstmt.setDate(2, sqlDate);
            cstmt.setString(3, email);

            cstmt.executeUpdate();
            System.out.println("✅ Thêm mới sinh viên thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi thêm sinh viên (có thể email đã tồn tại): " + e.getMessage());
        }
    }
}
