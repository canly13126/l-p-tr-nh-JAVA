
import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void display() {
        System.out.println("Mã sản phẩm: " + id);
        System.out.println("Tên sản phẩm: " + name);
        System.out.println("Giá sản phẩm: " + price);
        System.out.println("---------------------------");
    }
}

public class bai5ss7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> danhSach = new ArrayList<>();

        int luaChon;

        do {
            System.out.println("\n========== QUẢN LÝ SẢN PHẨM ==========");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ!");
                scanner.nextLine();
                continue;
            }

            luaChon = scanner.nextInt();
            scanner.nextLine();

            switch (luaChon) {
                case 1:
                    System.out.print("Nhập mã sản phẩm: ");
                    String id = scanner.nextLine();

                    boolean trungId = false;

                    for (Product p : danhSach) {
                        if (p.getId().equals(id)) {
                            trungId = true;
                            break;
                        }
                    }

                    if (trungId) {
                        System.out.println("Mã sản phẩm đã tồn tại!");
                        break;
                    }

                    System.out.print("Nhập tên sản phẩm: ");
                    String name = scanner.nextLine();

                    System.out.print("Nhập giá sản phẩm: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    if (price < 0 || !Double.isFinite(price)) {
                        System.out.println("Giá sản phẩm không hợp lệ!");
                        break;
                    }

                    danhSach.add(new Product(id, name, price));
                    System.out.println("Thêm sản phẩm thành công!");
                    break;

                case 2:
                    if (danhSach.isEmpty()) {
                        System.out.println("Danh sách sản phẩm đang trống!");
                    } else {
                        System.out.println("\n=== DANH SÁCH SẢN PHẨM ===");

                        for (Product p : danhSach) {
                            p.display();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                    String idCapNhat = scanner.nextLine();

                    Product sanPhamCapNhat = null;

                    for (Product p : danhSach) {
                        if (p.getId().equals(idCapNhat)) {
                            sanPhamCapNhat = p;
                            break;
                        }
                    }

                    if (sanPhamCapNhat == null) {
                        System.out.println("Không tìm thấy sản phẩm!");
                        break;
                    }

                    System.out.print("Nhập tên mới: ");
                    String tenMoi = scanner.nextLine();

                    System.out.print("Nhập giá mới: ");
                    double giaMoi = scanner.nextDouble();
                    scanner.nextLine();

                    if (giaMoi < 0 || !Double.isFinite(giaMoi)) {
                        System.out.println("Giá sản phẩm không hợp lệ!");
                        break;
                    }

                    sanPhamCapNhat.setName(tenMoi);
                    sanPhamCapNhat.setPrice(giaMoi);

                    System.out.println("Cập nhật sản phẩm thành công!");
                    break;

                case 4:
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String idXoa = scanner.nextLine();

                    boolean daXoa = false;

                    for (int i = 0; i < danhSach.size(); i++) {
                        if (danhSach.get(i).getId().equals(idXoa)) {
                            danhSach.remove(i);
                            daXoa = true;
                            break;
                        }
                    }

                    if (daXoa) {
                        System.out.println("Xóa sản phẩm thành công!");
                    } else {
                        System.out.println("Không tìm thấy sản phẩm!");
                    }
                    break;

                case 5:
                    System.out.println("Kết thúc chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (luaChon != 5);

        scanner.close();
    }
}
