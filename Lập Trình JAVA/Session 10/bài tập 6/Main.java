import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderManager manager = new OrderManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ ĐƠN HÀNG =====");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Sửa đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("4. Hiển thị danh sách đơn hàng");
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
                    String orderId;
                    while (true) {
                        try {
                            System.out.print("Nhập mã đơn hàng: ");
                            orderId = scanner.nextLine().trim();
                            if (orderId.isEmpty()) {
                                throw new IllegalArgumentException("Mã đơn hàng không được để trống!");
                            }
                            if (manager.findIndexById(orderId) != -1) {
                                throw new IllegalArgumentException("Mã đơn hàng này đã tồn tại!");
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ Lỗi: " + e.getMessage());
                        }
                    }

                    String customerName = inputCustomerName(scanner);
                    manager.add(new Order(orderId, customerName));
                    break;

                case 2:
                    if (manager.getSize() == 0) {
                        System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
                        break;
                    }
                    manager.display();
                    try {
                        System.out.print("Nhập mã đơn hàng cần sửa: ");
                        String updateId = scanner.nextLine().trim();
                        int updateIndex = manager.findIndexById(updateId);
                        if (updateIndex == -1) {
                            throw new IllegalArgumentException("Mã đơn hàng \"" + updateId + "\" không tồn tại!");
                        }
                        String newCustomerName = inputCustomerName(scanner);
                        manager.update(updateIndex, new Order(updateId, newCustomerName));
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Lỗi: " + e.getMessage());
                    }
                    break;

                case 3:
                    if (manager.getSize() == 0) {
                        System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
                        break;
                    }
                    manager.display();
                    try {
                        System.out.print("Nhập mã đơn hàng cần xóa: ");
                        String deleteId = scanner.nextLine().trim();
                        int deleteIndex = manager.findIndexById(deleteId);
                        if (deleteIndex == -1) {
                            throw new IllegalArgumentException("Mã đơn hàng \"" + deleteId + "\" không tồn tại!");
                        }
                        manager.delete(deleteIndex);
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Lỗi: " + e.getMessage());
                    }
                    break;

                case 4:
                    manager.display();
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

    private static String inputCustomerName(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập tên khách hàng: ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Tên khách hàng không được để trống!");
                }
                return name;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }
}
