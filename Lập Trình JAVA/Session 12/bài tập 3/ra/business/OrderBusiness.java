package ra.business;

import ra.entity.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderBusiness {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Scanner scanner) {
        Order order = new Order();
        order.inputData(scanner);
        orders.add(order);
        System.out.println("✅ Thêm đơn hàng thành công! (Trạng thái mặc định: Pending)");
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH ĐƠN HÀNG (GIÁ TRỊ GIẢM DẦN) ---");
        orders.stream()
                .sorted(Comparator.comparingDouble(Order::getOrderAmount).reversed())
                .forEach(System.out::println);
    }

    public void updateOrderStatus(Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
            return;
        }

        int id = inputOrderId(scanner, "cập nhật trạng thái");
        Optional<Order> optionalOrder = findById(id);

        optionalOrder.ifPresentOrElse(
            order -> {
                String currentStatus = order.getStatus();
                if (currentStatus.equals("Pending")) {
                    order.setStatus("Shipped");
                    System.out.println("✅ Cập nhật thành công: Pending -> Shipped");
                } else if (currentStatus.equals("Shipped")) {
                    order.setStatus("Delivered");
                    System.out.println("✅ Cập nhật thành công: Shipped -> Delivered");
                } else {
                    System.out.println("⚠️ Đơn hàng đã ở trạng thái Delivered, không thể cập nhật thêm!");
                }
            },
            () -> System.out.println("❌ Không tìm thấy đơn hàng có mã ID = " + id + ".")
        );
    }

    public void deleteOrder(Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
            return;
        }

        int id = inputOrderId(scanner, "xóa");
        Optional<Order> optionalOrder = findById(id);

        optionalOrder.ifPresentOrElse(
            order -> {
                if (order.getStatus().equals("Pending")) {
                    orders.remove(order);
                    System.out.println("✅ Đã xóa đơn hàng có mã ID = " + id + " thành công!");
                } else {
                    System.out.println("❌ Lỗi: Chỉ được phép xóa đơn hàng khi ở trạng thái Pending (hiện tại: " + order.getStatus() + ")!");
                }
            },
            () -> System.out.println("❌ Không tìm thấy đơn hàng có mã ID = " + id + ".")
        );
    }

    public void searchByCustomerName(Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
            return;
        }

        System.out.print("Nhập tên khách hàng cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Order> results = orders.stream()
                .filter(o -> o.getCustomerName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("❌ Không tìm thấy đơn hàng nào của khách hàng \"" + keyword + "\".");
        } else {
            System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
            results.forEach(System.out::println);
        }
    }

    public void statisticsTotalOrders() {
        System.out.println("📊 Tổng số đơn hàng hiện có: " + orders.size());
    }

    public void statisticsDeliveredRevenue() {
        double totalRevenue = orders.stream()
                .filter(o -> o.getStatus().equals("Delivered"))
                .mapToDouble(Order::getOrderAmount)
                .sum();
        System.out.printf("💰 Tổng doanh thu từ các đơn hàng Delivered: %.2f\n", totalRevenue);
    }

    public void statisticsCountByStatus() {
        long pendingCount = orders.stream().filter(o -> o.getStatus().equals("Pending")).count();
        long shippedCount = orders.stream().filter(o -> o.getStatus().equals("Shipped")).count();
        long deliveredCount = orders.stream().filter(o -> o.getStatus().equals("Delivered")).count();

        System.out.println("\n--- THỐNG KÊ SỐ LƯỢNG ĐƠN HÀNG THEO TRẠNG THÁI ---");
        System.out.println("- Pending   : " + pendingCount + " đơn hàng");
        System.out.println("- Shipped   : " + shippedCount + " đơn hàng");
        System.out.println("- Delivered : " + deliveredCount + " đơn hàng");
    }

    public void findMaxAmountOrder() {
        if (orders.isEmpty()) {
            System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
            return;
        }

        float maxAmount = (float) orders.stream()
                .mapToDouble(Order::getOrderAmount)
                .max()
                .orElse(0);

        System.out.println("\n--- ĐƠN HÀNG CÓ GIÁ TRỊ LỚN NHẤT (" + String.format("%.2f", maxAmount) + ") ---");
        orders.stream()
                .filter(o -> o.getOrderAmount() == maxAmount)
                .forEach(System.out::println);
    }

    private Optional<Order> findById(int id) {
        return orders.stream()
                .filter(o -> o.getOrderId() == id)
                .findFirst();
    }

    private int inputOrderId(Scanner scanner, String action) {
        while (true) {
            try {
                System.out.print("Nhập mã đơn hàng cần " + action + ": ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Mã đơn hàng phải là một số nguyên hợp lệ!");
            }
        }
    }
}
