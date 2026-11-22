import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private boolean isBookExists(Connection conn, String title, String author) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM Book WHERE LOWER(title) = LOWER(?) AND LOWER(author) = LOWER(?)";
        try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private boolean isIdExists(Connection conn, int id) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM Book WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void addBook(Book book) {
        String insertSql = "INSERT INTO Book (title, author, published_year, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (isBookExists(conn, book.getTitle(), book.getAuthor())) {
                System.out.println("❌ Thêm thất bại: Sách \"" + book.getTitle() + "\" của tác giả \"" + book.getAuthor() + "\" đã tồn tại!");
                return;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setInt(3, book.getPublishedYear());
                pstmt.setDouble(4, book.getPrice());

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Thêm sách mới thành công!");
                } else {
                    System.out.println("❌ Thêm sách mới thất bại!");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu khi thêm sách: " + e.getMessage());
        }
    }

    public void updateBook(int id, Book book) {
        String updateSql = "UPDATE Book SET title = ?, author = ?, published_year = ?, price = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (!isIdExists(conn, id)) {
                System.out.println("❌ Cập nhật thất bại: Không tìm thấy sách có ID = " + id + ".");
                return;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setInt(3, book.getPublishedYear());
                pstmt.setDouble(4, book.getPrice());
                pstmt.setInt(5, id);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Cập nhật thông tin sách thành công!");
                } else {
                    System.out.println("❌ Cập nhật thông tin sách thất bại!");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu khi cập nhật sách: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String deleteSql = "DELETE FROM Book WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (!isIdExists(conn, id)) {
                System.out.println("❌ Xóa thất bại: Không tìm thấy sách có ID = " + id + ".");
                return;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
                pstmt.setInt(1, id);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Đã xóa sách có ID = " + id + " thành công!");
                } else {
                    System.out.println("❌ Xóa sách thất bại!");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu khi xóa sách: " + e.getMessage());
        }
    }

    public void findBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String searchSql = "SELECT * FROM Book WHERE LOWER(author) LIKE LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(searchSql)) {

            pstmt.setString(1, "%" + author + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getDouble("price")
                    ));
                }
            }

            if (books.isEmpty()) {
                System.out.println("❌ Không tìm thấy cuốn sách nào của tác giả \"" + author + "\".");
            } else {
                System.out.println("\n--- DANH SÁCH SÁCH CỦA TÁC GIẢ \"" + author + "\" ---");
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu khi tìm kiếm sách: " + e.getMessage());
        }
    }

    public void listAllBooks() {
        List<Book> books = new ArrayList<>();
        String selectSql = "SELECT * FROM Book";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("published_year"),
                    rs.getDouble("price")
                ));
            }

            if (books.isEmpty()) {
                System.out.println("⚠️ Thư viện hiện chưa có cuốn sách nào.");
            } else {
                System.out.println("\n--- DANH SÁCH TẤT CẢ SÁCH TRONG THƯ VIỆN ---");
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu khi hiển thị danh sách sách: " + e.getMessage());
        }
    }
}
