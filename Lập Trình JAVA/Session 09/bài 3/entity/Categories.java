
package entity;

import java.util.Scanner;

public class Categories implements IShop {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName,
                      String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int index) {
        int maxId = 0;

        for (int i = 0; i < index; i++) {
            if (arrCategories[i].getCatalogId() > maxId) {
                maxId = arrCategories[i].getCatalogId();
            }
        }

        catalogId = maxId + 1;

        while (true) {
            System.out.print("Nhập tên danh mục: ");
            String name = scanner.nextLine().trim();

            boolean trungTen = false;

            for (int i = 0; i < index; i++) {
                if (arrCategories[i].getCatalogName().equalsIgnoreCase(name)) {
                    trungTen = true;
                    break;
                }
            }

            if (name.isEmpty() || name.length() > 50) {
                System.out.println("Tên danh mục phải từ 1 đến 50 ký tự!");
            } else if (trungTen) {
                System.out.println("Tên danh mục đã tồn tại!");
            } else {
                catalogName = name;
                break;
            }
        }

        System.out.print("Nhập mô tả danh mục: ");
        descriptions = scanner.nextLine();

        while (true) {
            System.out.print("Nhập trạng thái (true/false): ");
            String status = scanner.nextLine().trim();

            if (status.equalsIgnoreCase("true")
                    || status.equalsIgnoreCase("false")) {
                catalogStatus = Boolean.parseBoolean(status);
                break;
            }

            System.out.println("Trạng thái chỉ nhận true hoặc false!");
        }
    }

    @Override
    public void displayData() {
        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Tên danh mục: " + catalogName);
        System.out.println("Mô tả: " + descriptions);
        System.out.println("Trạng thái: "
                + (catalogStatus ? "Hoạt động" : "Không hoạt động"));
        System.out.println("--------------------------------");
    }
}
