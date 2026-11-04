
package presentation;

import entity.Categories;
import entity.Product;
import java.util.Scanner;

public class ShopManagement {
    static Scanner scanner = new Scanner(System.in);

    static Categories[] arrCategories = new Categories[100];
    static Product[] arrProduct = new Product[100];

    static int indexCatalog = 0;
    static int indexProduct = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n************** SHOP MENU **************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    categoryMenu();
                    break;
                case 2:
                    productMenu();
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void categoryMenu() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ DANH MỤC =====");
            System.out.println("1. Hiển thị danh sách danh mục");
            System.out.println("2. Thêm danh mục mới");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Quay lại");
            System.out.print("Lựa chọn: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    if (indexCatalog == 0) {
                        System.out.println("Chưa có danh mục!");
                    } else {
                        for (int i = 0; i < indexCatalog; i++) {
                            arrCategories[i].displayData();
                        }
                    }
                    break;

                case 2:
                    if (indexCatalog >= arrCategories.length) {
                        System.out.println("Danh sách danh mục đã đầy!");
                        break;
                    }

                    Categories category = new Categories();
                    category.inputData(scanner, arrCategories, indexCatalog);

                    arrCategories[indexCatalog] = category;
                    indexCatalog++;

                    System.out.println("Thêm danh mục thành công!");
                    break;

                case 3:
                    System.out.print("Nhập mã danh mục cần cập nhật: ");
                    int idSua = readInt();

                    int viTriSua = -1;

                    for (int i = 0; i < indexCatalog; i++) {
                        if (arrCategories[i].getCatalogId() == idSua) {
                            viTriSua = i;
                            break;
                        }
                    }

                    if (viTriSua == -1) {
                        System.out.println("Không tìm thấy danh mục!");
                        break;
                    }

                    Categories[] danhSachKhac = new Categories[100];
                    int soLuongKhac = 0;

                    for (int i = 0; i < indexCatalog; i++) {
                        if (i != viTriSua) {
                            danhSachKhac[soLuongKhac++] = arrCategories[i];
                        }
                    }

                    Categories categoryMoi = new Categories();
                    categoryMoi.inputData(scanner, danhSachKhac, soLuongKhac);
                    categoryMoi.setCatalogId(idSua);

                    arrCategories[viTriSua] = categoryMoi;

                    System.out.println("Cập nhật danh mục thành công!");
                    break;

                case 4:
                    System.out.print("Nhập mã danh mục cần xóa: ");
                    int idXoa = readInt();

                    int viTriXoa = -1;

                    for (int i = 0; i < indexCatalog; i++) {
                        if (arrCategories[i].getCatalogId() == idXoa) {
                            viTriXoa = i;
                            break;
                        }
                    }

                    if (viTriXoa == -1) {
                        System.out.println("Không tìm thấy danh mục!");
                        break;
                    }

                    boolean coSanPham = false;

                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getCatalogId() == idXoa) {
                            coSanPham = true;
                            break;
                        }
                    }

                    if (coSanPham) {
                        System.out.println("Không thể xóa danh mục đang có sản phẩm!");
                        break;
                    }

                    for (int i = viTriXoa; i < indexCatalog - 1; i++) {
                        arrCategories[i] = arrCategories[i + 1];
                    }

                    arrCategories[--indexCatalog] = null;
                    System.out.println("Xóa danh mục thành công!");
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void productMenu() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Quay lại");
            System.out.print("Lựa chọn: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    if (indexProduct == 0) {
                        System.out.println("Chưa có sản phẩm!");
                    } else {
                        for (int i = 0; i < indexProduct; i++) {
                            arrProduct[i].displayData();
                        }
                    }
                    break;

                case 2:
                    if (indexCatalog == 0) {
                        System.out.println("Vui lòng thêm danh mục trước!");
                        break;
                    }

                    if (indexProduct >= arrProduct.length) {
                        System.out.println("Danh sách sản phẩm đã đầy!");
                        break;
                    }

                    Product product = new Product();
                    product.inputData(scanner, arrProduct, indexProduct,
                            arrCategories, indexCatalog);

                    arrProduct[indexProduct++] = product;
                    System.out.println("Thêm sản phẩm thành công!");
                    break;

                case 3:
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                    String idSua = scanner.nextLine().trim();

                    int viTriSua = -1;

                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getProductId().equalsIgnoreCase(idSua)) {
                            viTriSua = i;
                            break;
                        }
                    }

                    if (viTriSua == -1) {
                        System.out.println("Không tìm thấy sản phẩm!");
                        break;
                    }

                    Product[] danhSachKhac = new Product[100];
                    int soLuongKhac = 0;

                    for (int i = 0; i < indexProduct; i++) {
                        if (i != viTriSua) {
                            danhSachKhac[soLuongKhac++] = arrProduct[i];
                        }
                    }

                    Product productMoi = new Product();
                    productMoi.inputData(scanner, danhSachKhac, soLuongKhac,
                            arrCategories, indexCatalog);

                    arrProduct[viTriSua] = productMoi;
                    System.out.println("Cập nhật sản phẩm thành công!");
                    break;

                case 4:
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String idXoa = scanner.nextLine().trim();

                    int viTriXoa = -1;

                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProduct[i].getProductId().equalsIgnoreCase(idXoa)) {
                            viTriXoa = i;
                            break;
                        }
                    }

                   