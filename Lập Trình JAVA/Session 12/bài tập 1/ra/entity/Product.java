package ra.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {
    private static int autoId = 1;
    private int productId;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    public Product() {
        this.productId = autoId++;
    }

    public Product(int productId, String productName, float price, String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void inputData(Scanner scanner) {
        inputData(scanner, new ArrayList<>());
    }

    public void inputData(Scanner scanner, List<Product> existingProducts) {
        this.productName = inputProductName(scanner, existingProducts, this.productId);
        this.price = inputPrice(scanner);
        this.category = inputCategory(scanner);
        this.quantity = inputQuantity(scanner);
    }

    public static String inputProductName(Scanner scanner, List<Product> existingProducts, int currentId) {
        while (true) {
            System.out.print("Nhập tên sản phẩm (10 - 50 ký tự): ");
            String name = scanner.nextLine().trim();
            if (name.length() < 10 || name.length() > 50) {
                System.out.println("❌ Lỗi: Tên sản phẩm phải có độ dài từ 10 đến 50 ký tự!");
                continue;
            }
            boolean isDuplicate = false;
            for (Product p : existingProducts) {
                if (p.getProductId() != currentId && p.getProductName().equalsIgnoreCase(name)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                System.out.println("❌ Lỗi: Tên sản phẩm đã tồn tại, vui lòng nhập tên khác!");
                continue;
            }
            return name;
        }
    }

    public static float inputPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm (> 0): ");
                float price = Float.parseFloat(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("❌ Lỗi: Giá sản phẩm phải lớn hơn 0!");
                    continue;
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Giá sản phẩm phải là một số thực hợp lệ!");
            }
        }
    }

    public static String inputCategory(Scanner scanner) {
        while (true) {
            System.out.print("Nhập loại sản phẩm (tối đa 200 ký tự): ");
            String category = scanner.nextLine().trim();
            if (category.isEmpty()) {
                System.out.println("❌ Lỗi: Loại sản phẩm không được để trống!");
                continue;
            }
            if (category.length() > 200) {
                System.out.println("❌ Lỗi: Loại sản phẩm không được vượt quá 200 ký tự!");
                continue;
            }
            return category;
        }
    }

    public static int inputQuantity(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập số lượng tồn kho (>= 0): ");
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.println("❌ Lỗi: Số lượng tồn kho phải lớn hơn hoặc bằng 0!");
                    continue;
                }
                return quantity;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Số lượng tồn kho phải là một số nguyên hợp lệ!");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Mã SP: %-5d | Tên SP: %-25s | Giá: %-10.2f | Danh mục: %-18s | Tồn kho: %d",
                productId, productName, price, category, quantity);
    }
}
