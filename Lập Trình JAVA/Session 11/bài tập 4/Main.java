import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Product> productMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Product Management System ---");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Sửa sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Hiển thị danh sách sản phẩm");
            System.out.println("5. Lọc sản phẩm có giá lớn hơn 100");
            System.out.println("6. Tính tổng giá trị sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng (0-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 0 đến 6!");
                continue;
            }

            switch (choice) {
                case 1:
                    addProduct(scanner, productMap);
                    break;
                case 2:
                    updateProduct(scanner, productMap);
                    break;
                case 3:
                    deleteProduct(scanner, productMap);
                    break;
                case 4:
                    displayProducts(productMap);
                    break;
                case 5:
                    filterProductsByPrice(productMap);
                    break;
                case 6:
                    calculateTotalValue(productMap);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void addProduct(Scanner scanner, Map<Integer, Product> productMap) {
        int id;
        while (true) {
            try {
                System.out.print("Nhập ID sản phẩm: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ Lỗi: ID phải là số nguyên dương!");
                    continue;
                }
                if (productMap.containsKey(id)) {
                    System.out.println("❌ Lỗi: ID sản phẩm này đã tồn tại!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là một số nguyên hợp lệ!");
            }
        }

        String name = inputName(scanner);
        double price = inputPrice(scanner);

        productMap.put(id, new Product(id, name, price));
        System.out.println("✅ Thêm sản phẩm thành công!");
    }

    private static void updateProduct(Scanner scanner, Map<Integer, Product> productMap) {
        if (productMap.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }

        int id;
        while (true) {
            try {
                System.out.print("Nhập ID sản phẩm cần sửa: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là một số nguyên hợp lệ!");
            }
        }

        if (!productMap.containsKey(id)) {
            System.out.println("❌ Không tìm thấy sản phẩm có ID = " + id + ".");
            return;
        }

        String newName = inputName(scanner);
        double newPrice = inputPrice(scanner);

        Product product = productMap.get(id);
        product.setName(newName);
        product.setPrice(newPrice);
        System.out.println("✅ Cập nhật sản phẩm thành công!");
    }

    private static void deleteProduct(Scanner scanner, Map<Integer, Product> productMap) {
        if (productMap.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }

        int id;
        while (true) {
            try {
                System.out.print("Nhập ID sản phẩm cần xóa: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là một số nguyên hợp lệ!");
            }
        }

        if (productMap.remove(id) != null) {
            System.out.println("✅ Đã xóa sản phẩm có ID = " + id + " thành công!");
        } else {
            System.out.println("❌ Không tìm thấy sản phẩm có ID = " + id + ".");
        }
    }

    private static void displayProducts(Map<Integer, Product> productMap) {
        if (productMap.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }

        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        productMap.values().forEach(System.out::println);
    }

    private static void filterProductsByPrice(Map<Integer, Product> productMap) {
        if (productMap.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }

        List<Product> filtered = productMap.values().stream()
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("⚠️ Không có sản phẩm nào có giá lớn hơn 100.");
        } else {
            System.out.println("\n--- SẢN PHẨM CÓ GIÁ LỚN HƠN 100 ---");
            filtered.forEach(System.out::println);
        }
    }

    private static void calculateTotalValue(Map<Integer, Product> productMap) {
        double total = productMap.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.printf("💰 Tổng giá trị của tất cả sản phẩm: %.2f\n", total);
    }

    private static String inputName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("❌ Lỗi: Tên sản phẩm không được để trống!");
        }
    }

    private static double inputPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                double price = Double.parseDouble(scanner.nextLine());
                if (price < 0) {
                    System.out.println("❌ Lỗi: Giá sản phẩm không được âm!");
                    continue;
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Giá sản phẩm phải là một số thực hợp lệ!");
            }
        }
    }
}
