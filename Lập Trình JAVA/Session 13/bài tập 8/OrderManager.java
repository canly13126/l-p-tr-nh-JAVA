import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderManager {
    private boolean isProductExists(Connection conn, String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Product WHERE LOWER(name) = LOWER(?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (name, price) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (isProductExists(conn, product.getName())) {
                System.out.println("❌ Thêm thất bại: Sản phẩm \"" + product.getName() + "\" đã tồn tại!");
                return;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getPrice());
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Thêm sản phẩm mới thành công!");
                } else {
                    System.out.println("❌ Thêm sản phẩm mới thất bại!");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu khi thêm sản phẩm: " + e.getMessage());
        }
    }

    public void updateCustomer(int customerId, Customer customer) {
        String checkSql = "SELECT COUNT(*) FROM Customer WHERE id = ?";
        String updateSql = "UPDATE Customer SET name = ?, email = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            boolean exists = false;
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, customerId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        exists = true;
                    }
                }
            }

            if (!exists) {
                System.out.println("❌ Cập nhật thất bại: Không tìm thấy khách hàng có ID = " + customerId + ".");
                return;
            }

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, customer.getName());
                updateStmt.setString(2, customer.getEmail());
                updateStmt.setInt(3, customerId);
                int rows = updateStmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Cập nhật thông tin khách hàng thành công!");
                } else {
                    System.out.println("❌ Cập nhật thông tin khách hàng thất bại!");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu khi cập nhật khách hàng: " + e.getMessage());
        }
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi tìm sản phẩm: " + e.getMessage());
        }
        return null;
    }

    public boolean isCustomerExists(int customerId) {
        String sql = "SELECT COUNT(*) FROM Customer WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi kiểm tra khách hàng: " + e.getMessage());
        }
        return false;
    }

    public void createOrder(Order order) {
        if (order.getOrderDetails() == null || order.getOrderDetails().isEmpty()) {
            System.out.println("❌ Lỗi: Đơn hàng phải có ít nhất một sản phẩm!");
            return;
        }

        double totalAmount = 0;
        for (OrderDetail detail : order.getOrderDetails()) {
            totalAmount += detail.getPriceSell() * detail.getQuantity();
        }
        order.setTotalAmount(totalAmount);

        String insertOrderSql = "INSERT INTO `Order` (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
        String insertDetailSql = "INSERT INTO OrderDetail (product_id, order_id, quantity, priceSell) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement orderStmt = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, order.getCustomerId());
                orderStmt.setDate(2, order.getOrderDate());
                orderStmt.setDouble(3, order.getTotalAmount());
                orderStmt.executeUpdate();

                int generatedOrderId = 0;
                try (ResultSet rs = orderStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedOrderId = rs.getInt(1);
                    }
                }

                try (PreparedStatement detailStmt = conn.prepareStatement(insertDetailSql)) {
                    for (OrderDetail detail : order.getOrderDetails()) {
                        detailStmt.setInt(1, detail.getProductId());
                        detailStmt.setInt(2, generatedOrderId);
                        detailStmt.setInt(3, detail.getQuantity());
                        detailStmt.setDouble(4, detail.getPriceSell());
                        detailStmt.addBatch();
                    }
                    detailStmt.executeBatch();
                }

                conn.commit();
                System.out.printf("✅ Tạo đơn hàng thành công! (Mã ĐH: %d | Tổng tiền: %.2f)\n",
                        generatedOrderId, totalAmount);
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("❌ Lỗi khi lưu đơn hàng, đã hủy giao dịch: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void listAllOrders() {
        String sql = "SELECT o.id, c.name AS customer_name, o.order_date, o.total_amount " +
                     "FROM `Order` o JOIN Customer c ON o.customer_id = c.id " +
                     "ORDER BY o.id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            boolean hasData = false;
            System.out.println("\n--- DANH SÁCH TẤT CẢ ĐƠN HÀNG ---");
            while (rs.next()) {
                hasData = true;
                System.out.printf("Mã ĐH: %-5d | Khách hàng: %-25s | Ngày đặt: %-12s | Tổng tiền: %.2f\n",
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getDate("order_date"),
                        rs.getDouble("total_amount"));
            }

            if (!hasData) {
                System.out.println("⚠️ Hiện chưa có đơn hàng nào trong hệ thống.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi hiển thị danh sách đơn hàng: " + e.getMessage());
        }
    }

    public void getOrdersByCustomer(int customerId) {
        String sql = "SELECT o.id, c.name AS customer_name, o.order_date, o.total_amount " +
                     "FROM `Order` o JOIN Customer c ON o.customer_id = c.id " +
                     "WHERE o.customer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hasData = false;
                System.out.println("\n--- ĐƠN HÀNG CỦA KHÁCH HÀNG CÓ ID = " + customerId + " ---");
                while (rs.next()) {
                    hasData = true;
                    System.out.printf("Mã ĐH: %-5d | Khách hàng: %-25s | Ngày đặt: %-12s | Tổng tiền: %.2f\n",
                            rs.getInt("id"),
                            rs.getString("customer_name"),
                            rs.getDate("order_date"),
                            rs.getDouble("total_amount"));
                }

                if (!hasData) {
                    System.out.println("❌ Không tìm thấy đơn hàng nào của khách hàng có ID = " + customerId + ".");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi tìm kiếm đơn hàng theo khách hàng: " + e.getMessage());
        }
    }
}
