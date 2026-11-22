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
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    System.out.println("ℹ️ Chức năng tìm kiếm sẽ được thực hiện ở bài tập tiếp theo!");
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
        String fullName = inputFullName(scanner);
        Date sqlDate = inputDateOfBirth(scanner);
        String email = inputEmail(scanner);

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

    private static void updateStudent(Scanner scanner) {
        int id = inputStudentId(scanner, "sửa");
        Student existingStudent = findStudentById(id);

        if (existingStudent == null) {
            System.out.println("❌ Không tìm thấy sinh viên có ID = " + id + ".");
            return;
        }

        System.out.println("Thông tin hiện tại: " + existingStudent);
        String newFullName = inputFullName(scanner);
        Date newSqlDate = inputDateOfBirth(scanner);
        String newEmail = inputEmail(scanner);

        String sql = "{call update_student(?, ?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id);
            cstmt.setString(2, newFullName);
            cstmt.setDate(3, newSqlDate);
            cstmt.setString(4, newEmail);

            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Cập nhật thông tin sinh viên thành công!");
            } else {
                System.out.println("❌ Cập nhật thất bại!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi cập nhật sinh viên: " + e.getMessage());
        }
    }

    private static void deleteStudent(Scanner scanner) {
        int id = inputStudentId(scanner, "xóa");
        Student existingStudent = findStudentById(id);

        if (existingStudent == null) {
            System.out.println("❌ Không tìm thấy sinh viên có ID = " + id + ".");
            return;
        }

        String sql = "{call delete_student(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id);
            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Đã xóa sinh viên có ID = " + id + " thành công!");
            } else {
                System.out.println("❌ Xóa sinh viên thất bại!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi xóa sinh viên: " + e.getMessage());
        }
    }

    private static Student findStudentById(int id) {
        String sql = "{call find_student_by_id(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id);
            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi kiểm tra sinh viên: " + e.getMessage());
        }
        return null;
    }

    private static int inputStudentId(Scanner scanner, String action) {
        while (true) {
            try {
                System.out.print("Nhập ID sinh viên cần " + action + ": ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("❌ Lỗi: ID sinh viên không được để trống!");
                    continue;
                }
                int id = Integer.parseInt(input);
                if (id <= 0) {
                    System.out.println("❌ Lỗi: ID sinh viên phải là số nguyên dương!");
                    continue;
                }
                return id;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID sinh viên phải là một số nguyên hợp lệ!");
            }
        }
    }

    private static String inputFullName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập họ và tên sinh viên: ");
            String fullName = scanner.nextLine().trim();
            if (!fullName.isEmpty()) {
                return fullName;
            }
            System.out.println("❌ Lỗi: Họ và tên không được để trống!");
        }
    }

    private static Date inputDateOfBirth(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập ngày sinh (yyyy-MM-dd, ví dụ 2004-05-20): ");
                String dobInput = scanner.nextLine().trim();
                if (dobInput.isEmpty()) {
                    System.out.println("❌ Lỗi: Ngày sinh không được để trống!");
                    continue;
                }
                LocalDate localDate = LocalDate.parse(dobInput);
                return Date.valueOf(localDate);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Định dạng ngày sinh không hợp lệ! Vui lòng nhập đúng dạng yyyy-MM-dd.");
            }
        }
    }

    private static String inputEmail(Scanner scanner) {
        while (true) {
            System.out.print("Nhập email sinh viên: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("❌ Lỗi: Email không được để trống!");
                continue;
            }
            if (!email.contains("@")) {
                System.out.println("❌ Lỗi: Email không đúng định dạng!");
                continue;
            }
            return email;
        }
    }
}
