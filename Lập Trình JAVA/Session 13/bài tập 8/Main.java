import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ ĐƠN HÀNG TRONG CỬA HÀNG =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Cập nhật thông tin khách hàng");
            System.out.println("3. Tạo đơn hàng mới");
            System.out.println("4. Hiển thị danh sách đơn hàng");
            System.out.println("5. Tìm kiếm đơn hàng theo khách hàng");
            System.out.println("6. Thoát");
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
                    String productName = inputNonEmptyString(scanner, "Nhập tên sản phẩm: ", "Tên sản phẩm");
                    double productPrice = inputPositiveDouble(scanner, "Nhập giá sản phẩm: ", "Giá sản phẩm");
                    orderManager.addProduct(new Product(productName, productPrice));
                    break;

                case 2:
                    int customerId = inputPositiveInt(scanner, "Nhập ID khách hàng cần cập nhật: ", "ID khách hàng");
                    String newName = inputNonEmptyString(scanner, "Nhập tên khách hàng mới: ", "Tên khách hàng");
                    String newEmail = inputEmail(scanner);
                    orderManager.updateCustomer(customerId, new Customer(newName, newEmail));
                    break;

                case 3:
                    handleCreateOrder(scanner, orderManager);
                    break;

                case 4:
                    orderManager.listAllOrders();
                    break;

                case 5:
                    int searchCustomerId = inputPositiveInt(scanner, "Nhập ID khách hàng cần tìm đơn hàng: ", "ID khách hàng");
                    orderManager.getOrdersByCustomer(searchCustomerId);
                    break;

                case 6:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 6!");
            }
        }
    }

    private static void handleCreateOrder(Scanner scanner, OrderManager orderManager) {
        int customerId = inputPositiveInt(scanner, "Nhập ID khách hàng đặt đơn: ", "ID khách hàng");
        if (!orderManager.isCustomerExists(customerId)) {
            System.out.println("❌ Lỗi: Khách hàng có ID = " + customerId + " không tồn tại!");
            return;
        }

        List<OrderDetail> details = new ArrayList<>();
        while (true) {
            int productId = inputPositiveInt(scanner, "Nhập ID sản phẩm muốn mua: ", "ID sản phẩm");
            Product product = orderManager.getProductById(productId);
            if (product == null) {
                System.out.println("❌ Lỗi: Sản phẩm có ID = " + productId + " không tồn tại!");
            } else {
                int quantity = inputPositiveInt(scanner, "Nhập số lượng mua (> 0): ", "Số lượng");
                details.add(new OrderDetail(product.getId(), quantity, product.getPrice()));
                System.out.println("✅ Đã thêm \"" + product.getName() + "\" (SL: " + quantity + ") vào đơn hàng.");
            }

            System.out.print("Bạn có muốn thêm sản phẩm khác vào đơn này không? (Y/N): ");
            String more = scanner.nextLine().trim();
            if (!more.equalsIgnoreCase("Y")) {
                break;
            }
        }

        if (!details.isEmpty()) {
            Order order = new Order(customerId, Date.valueOf(LocalDate.now()), 0, details);
            orderManager.createOrder(order);
        }
    }

    private static String inputNonEmptyString(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            try {
                System.out.print(prompt);
                String value = scanner.nextLine().trim();
                if (value.isEmpty()) {
                    throw new IllegalArgumentException(fieldName + " không được để trống!");
                }
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static String inputEmail(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập email khách hàng mới: ");
                String email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    throw new IllegalArgumentException("Email không được để trống!");
                }
                if (!email.contains("@")) {
                    throw new IllegalArgumentException("Email không đúng định dạng!");
                }
                return email;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static int inputPositiveInt(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException(fieldName + " không được để trống!");
                }
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    throw new IllegalArgumentException(fieldName + " phải lớn hơn 0!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: " + fieldName + " phải là một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static double inputPositiveDouble(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException(fieldName + " không được để trống!");
                }
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    throw new IllegalArgumentException(fieldName + " phải lớn hơn 0!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: " + fieldName + " phải là một số thực hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }
}
