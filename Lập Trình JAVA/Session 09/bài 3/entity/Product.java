
package entity;

import java.util.Scanner;

public class Product implements IShop {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price,
                   String description, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct,
                          int indexProduct, Categories[] arrCategories,
                          int indexCatalog) {

        while (true) {
            System.out.print("Nhập mã sản phẩm (C/S/A và 3 ký tự): ");
            String id = scanner.nextLine().trim().toUpperCase();

            boolean trungMa = false;

            for (int i = 0; i < indexProduct; i++) {
                if (arrProduct[i].getProductId().equalsIgnoreCase(id)) {
                    trungMa = true;
                    break;
                }
            }

            if (!id.matches("[CSA][A-Z0-9]{3}")) {
                System.out.println("Mã phải có 4 ký tự, bắt đầu bằng C, S hoặc A!");
            } else if (trungMa) {
                System.out.println("Mã sản phẩm đã tồn tại!");
            } else {
                productId = id;
                break;
            }
        }

        while (true) {
            System.out.print("Nhập tên sản phẩm (10-50 ký tự): ");
            String name = scanner.nextLine().trim();

            boolean trungTen = false;

            for (int i = 0; i < indexProduct; i++) {
                if (arrProduct[i].getProductName().equalsIgnoreCase(name)) {
                    trungTen = true;
                    break;
                }
            }

            if (name.length() < 10 || name.length() > 50) {
                System.out.println("Tên phải từ 10 đến 50 ký tự!");
            } else if (trungTen) {
                System.out.println("Tên sản phẩm đã tồn tại!");
            } else {
                productName = name;
                break;
            }
        }

        while (true) {
            System.out.print("Nhập giá sản phẩm: ");

            try {
                float inputPrice = Float.parseFloat(scanner.nextLine().trim());

                if (Float.isFinite(inputPrice) && inputPrice > 0) {
                    price = inputPrice;
                    break;
                }
            } catch (NumberFormatException e) {
                // Giá không hợp lệ
            }

            System.out.println("Giá phải lớn hơn 0!");
        }

        System.out.print("Nhập mô tả sản phẩm: ");
        description = scanner.nextLine();

        while (true) {
            System.out.println("\nDanh sách danh mục:");

            for (int i = 0; i < indexCatalog; i++) {
                System.out.println(arrCategories[i].getCatalogId()
                        + ". " + arrCategories[i].getCatalogName());
            }

            System.out.print("Chọn mã danh mục: ");

            try {
                int id = Integer.parseInt(scanner.nextLine().trim());

                boolean tonTai = false;

                for (int i = 0; i < indexCatalog; i++) {
                    if (arrCategories[i].getCatalogId() == id) {
                        tonTai = true;
                        break;
                    }
                }

                if (tonTai) {
                    catalogId = id;
                    break;
                }
            } catch (NumberFormatException e) {
                // Mã không hợp lệ
            }

            System.out.println("Mã danh mục không tồn tại!");
        }

        while (true) {
            System.out.print("Trạng thái (0: Đang bán, 1: Hết hàng, 2: Không bán): ");

            try {
                int status = Integer.parseInt(scanner.nextLine().trim());

                if (status >= 0 && status <= 2) {
                    productStatus = status;
                    break;
                }
            } catch (NumberFormatException e) {
                // Trạng thái không hợp lệ
            }

            System.out.println("Trạng thái phải từ 0 đến 2!");
        }
    }

    @Override
    public void displayData() {
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.printf("Giá: %,.0f VND%n", price);
        System.out.println("Mô tả: " + description);
        System.out.println("Mã danh mục: " + catalogId);

        String status;

        switch (productStatus) {
            case 0:
                status = "Đang bán";
                break;
            case 1:
                status = "Hết hàng";
                break;
            default:
                status = "Không bán";
        }

        System.out.println("Trạng thái: " + status);
        System.out.println("--------------------------------");
    }
}
