
import java.util.ArrayList;
import java.util.Scanner;

class Category {
    private String id;
    private String name;
    private String description;

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void display() {
        System.out.println("Mã danh mục: " + id);
        System.out.println("Tên danh mục: " + name);
        System.out.println("Mô tả: " + description);
        System.out.println("---------------------------");
    }
}

public class bai6ss7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Category> danhSach = new ArrayList<>();

        int luaChon;

        do {
            System.out.println("\n========== QUẢN LÝ DANH MỤC ==========");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Hiển thị danh sách danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Tìm kiếm danh mục theo tên");
            System.out.println("6. Thoát");
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
                    System.out.print("Nhập mã danh mục: ");
                    String id = scanner.nextLine();

                    boolean trungId = false;

                    for (Category c : danhSach) {
                        if (c.getId().equals(id)) {
                            trungId = true;
                            break;
                        }
                    }

                    if (trungId) {
                        System.out.println("Mã danh mục đã tồn tại!");
                        break;
                    }

                    System.out.print("Nhập tên danh mục: ");
                    String name = scanner.nextLine();

                    System.out.print("Nhập mô tả: ");
                    String description = scanner.nextLine();

                    danhSach.add(new Category(id, name, description));
                    System.out.println("Thêm danh mục thành công!");
                    break;

                case 2:
                    if (danhSach.isEmpty()) {
                        System.out.println("Danh sách danh mục đang trống!");
                    } else {
                        System.out.println("\n=== DANH SÁCH DANH MỤC ===");

                        for (Category c : danhSach) {
                            c.display();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã danh mục cần cập nhật: ");
                    String idCapNhat = scanner.nextLine();

                    Category danhMucCapNhat = null;

                    for (Category c : danhSach) {
                        if (c.getId().equals(idCapNhat)) {
                            danhMucCapNhat = c;
                            break;
                        }
                    }

                    if (danhMucCapNhat == null) {
                        System.out.println("Không tìm thấy danh mục!");
                        break;
                    }

                    System.out.print("Nhập tên mới: ");
                    String tenMoi = scanner.nextLine();

                    System.out.print("Nhập mô tả mới: ");
                    String moTaMoi = scanner.nextLine();

                    danhMucCapNhat.setName(tenMoi);
                    danhMucCapNhat.setDescription(moTaMoi);

                    System.out.println("Cập nhật danh mục thành công!");
                    break;

                case 4:
                    System.out.print("Nhập mã danh mục cần xóa: ");
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
                        System.out.println("Xóa danh mục thành công!");
                    } else {
                        System.out.println("Không tìm thấy danh mục!");
                    }
                    break;

                case 5:
                    System.out.print("Nhập tên danh mục cần tìm: ");
                    String tenCanTim = scanner.nextLine().trim().toLowerCase();

                    boolean timThay = false;

                    for (Category c : danhSach) {
                        if (c.getName().toLowerCase().contains(tenCanTim)) {
                            c.display();
                            timThay = true;
                        }
                    }

                    if (!timThay) {
                        System.out.println("Không tìm thấy danh mục phù hợp!");
                    }
                    break;

                case 6:
                    System.out.println("Kết thúc chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (luaChon != 6);

        scanner.close();
    }
}
