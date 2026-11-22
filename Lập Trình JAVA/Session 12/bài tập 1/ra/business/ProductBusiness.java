package ra.business;

import ra.entity.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductBusiness {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Scanner scanner) {
        Product product = new Product();
        product.inputData(scanner, products);
        products.add(product);
        System.out.println("✅ Thêm sản phẩm thành công!");
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void updateProduct(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }

        int id = inputProductId(scanner, "cập nhật");
        Product targetProduct = findById(id);

        if (targetProduct == null) {
            System.out.println("❌ Không tìm thấy sản phẩm có mã ID = " + id + ".");
            return;
        }

        System.out.println("Thông tin hiện tại: " + targetProduct);
        targetProduct.setProductName(Product.inputProductName(scanner, products, id));
        targetProduct.setPrice(Product.inputPrice(scanner));
        targetProduct.setCategory(Product.inputCategory(scanner));
        targetProduct.setQuantity(Product.inputQuantity(scanner));

        System.out.println("✅ Cập nhật thông tin sản phẩm thành công!");
    }

    public void deleteProduct(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }

        int id = inputProductId(scanner, "xóa");
        Product targetProduct = findById(id);

        if (targetProduct == null) {
            System.out.println("❌ Không tìm thấy sản phẩm có mã ID = " + id + ".");
            return;
        }

        products.remove(targetProduct);
        System.out.println("✅ Đã xóa sản phẩm có mã ID = " + id + " thành công!");
    }

    public void searchProductByName(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }

        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(keyword)) {
                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ Không tìm thấy sản phẩm nào khớp với từ khóa \"" + keyword + "\".");
        }
    }

    public void sortByPriceAsc() {
        if (products.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }
        products.sort(Comparator.comparingDouble(Product::getPrice));
        System.out.println("✅ Đã sắp xếp sản phẩm theo giá tăng dần!");
        displayProducts();
    }

    public void sortByQuantityDesc() {
        if (products.isEmpty()) {
            System.out.println("⚠️ Danh sách sản phẩm hiện đang trống.");
            return;
        }
        products.sort(Comparator.comparingInt(Product::getQuantity).reversed());
        System.out.println("✅ Đã sắp xếp sản phẩm theo số lượng giảm dần!");
        displayProducts();
    }

    private Product findById(int id) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    private int inputProductId(Scanner scanner, String action) {
        while (true) {
            try {
                System.out.print("Nhập mã sản phẩm cần " + action + ": ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Mã sản phẩm phải là một số nguyên hợp lệ!");
            }
        }
    }
}
