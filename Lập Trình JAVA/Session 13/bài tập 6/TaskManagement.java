import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {
    public void addTask(String taskName, String status) {
        String sql = "{call add_task(?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, taskName);
            cstmt.setString(2, status);

            cstmt.executeUpdate();
            System.out.println("✅ Thêm công việc mới thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi thêm công việc: " + e.getMessage());
        }
    }

    public void listTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "{call list_tasks()}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                tasks.add(new Task(
                    rs.getInt("id"),
                    rs.getString("task_name"),
                    rs.getString("status")
                ));
            }

            if (tasks.isEmpty()) {
                System.out.println("⚠️ Danh sách công việc hiện đang trống.");
            } else {
                System.out.println("\n--- DANH SÁCH CÔNG VIỆC ---");
                for (Task task : tasks) {
                    System.out.println(task);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi lấy danh sách công việc: " + e.getMessage());
        }
    }

    public void updateTaskStatus(int taskId, String status) {
        String sql = "{call update_task_status(?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, taskId);
            cstmt.setString(2, status);

            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Cập nhật trạng thái công việc thành công!");
            } else {
                System.out.println("❌ Không tìm thấy công việc có ID = " + taskId + ".");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi cập nhật trạng thái: " + e.getMessage());
        }
    }

    public void deleteTask(int taskId) {
        String sql = "{call delete_task(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, taskId);

            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Đã xóa công việc có ID = " + taskId + " thành công!");
            } else {
                System.out.println("❌ Không tìm thấy công việc có ID = " + taskId + ".");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi xóa công việc: " + e.getMessage());
        }
    }

    public void searchTaskByName(String taskName) {
        List<Task> results = new ArrayList<>();
        String sql = "{call search_task_by_name(?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, taskName);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Task(
                        rs.getInt("id"),
                        rs.getString("task_name"),
                        rs.getString("status")
                    ));
                }
            }

            if (results.isEmpty()) {
                System.out.println("❌ Không tìm thấy công việc nào có tên chứa \"" + taskName + "\".");
            } else {
                System.out.println("\n--- KẾT QUẢ TÌM KIẾM CÔNG VIỆC ---");
                for (Task task : results) {
                    System.out.println(task);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi tìm kiếm công việc: " + e.getMessage());
        }
    }

    public void taskStatistics() {
        String sql = "{call task_statistics()}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            if (rs.next()) {
                int completed = rs.getInt("completed_count");
                int uncompleted = rs.getInt("uncompleted_count");
                System.out.println("\n--- THỐNG KÊ CÔNG VIỆC ---");
                System.out.println("- Số công việc đã hoàn thành  : " + completed);
                System.out.println("- Số công việc chưa hoàn thành: " + uncompleted);
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi thống kê công việc: " + e.getMessage());
        }
    }
}
