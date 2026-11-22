import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentManager {
    public void addStudents(List<Student> students) {
        if (students == null || students.size() < 3) {
            System.out.println("❌ Lỗi: Danh sách phải có ít nhất 3 sinh viên!");
            return;
        }

        String sql = "{call add_students(?, ?)}";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                for (Student student : students) {
                    if (student.getName() == null || student.getName().trim().isEmpty() || student.getAge() <= 0) {
                        throw new SQLException("Dữ liệu sinh viên không hợp lệ!");
                    }
                    cstmt.setString(1, student.getName());
                    cstmt.setInt(2, student.getAge());
                    cstmt.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("✅ Thêm danh sách " + students.size() + " sinh viên thành công (Transaction đã Commit)!");
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("⚠️ Đã hoàn tác dữ liệu (Rollback) do xảy ra lỗi!");
                } catch (SQLException ex) {
                    System.out.println("❌ Lỗi khi thực hiện Rollback: " + ex.getMessage());
                }
            }
            System.out.println("❌ Lỗi khi thêm sinh viên: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("❌ Lỗi khi đóng kết nối: " + e.getMessage());
                }
            }
        }
    }
}
