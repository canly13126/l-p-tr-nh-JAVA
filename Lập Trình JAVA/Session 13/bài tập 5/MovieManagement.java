import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManagement {
    public void addMovie(String title, String director, int year) {
        String sql = "{call add_movie(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, title);
            cstmt.setString(2, director);
            cstmt.setInt(3, year);

            cstmt.executeUpdate();
            System.out.println("✅ Thêm phim mới thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi thêm phim: " + e.getMessage());
        }
    }

    public void listMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "{call list_movies()}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("director"),
                    rs.getInt("release_year")
                ));
            }

            if (movies.isEmpty()) {
                System.out.println("⚠️ Danh sách phim hiện đang trống.");
            } else {
                System.out.println("\n--- DANH SÁCH PHIM ---");
                for (Movie movie : movies) {
                    System.out.println(movie);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi lấy danh sách phim: " + e.getMessage());
        }
    }

    public void updateMovie(int id, String title, String director, int year) {
        String sql = "{call update_movie(?, ?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id);
            cstmt.setString(2, title);
            cstmt.setString(3, director);
            cstmt.setInt(4, year);

            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Cập nhật thông tin phim thành công!");
            } else {
                System.out.println("❌ Không tìm thấy phim có ID = " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi cập nhật phim: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String sql = "{call delete_movie(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id);

            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Đã xóa phim có ID = " + id + " thành công!");
            } else {
                System.out.println("❌ Không tìm thấy phim có ID = " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi xóa phim: " + e.getMessage());
        }
    }
}
