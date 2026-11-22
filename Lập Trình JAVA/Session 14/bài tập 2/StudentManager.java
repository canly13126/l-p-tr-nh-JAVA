import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentManager {
    public void updateStudent(Student student) {
        String sql = "{call update_student(?, ?, ?)}";
        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                cstmt.setInt(1, student.getId());
                cstmt.setString(2, student.getName());
                cstmt.setInt(3, student.getAge());

                int rowsAffected = cstmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Không tìm thấy sinh viên có ID = " + student.getId() + " để cập nhật!");
                }
            }

            conn.commit();
            System.out.println("✅ Cập nhật thông tin sinh viên thành công (Transaction đã Commit)!");
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("⚠️ Đã hoàn tác dữ liệu (Rollback) do xảy ra lỗi!");
                } catch (SQLException ex) {
                    System.out.println("❌ Lỗi khi thực hiện Rollback: " + ex.getMessage());
                }
            }
            System.out.println("❌ Cập nhật thất bại: " + e.getMessage());
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
