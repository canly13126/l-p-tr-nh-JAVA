
import java.util.Arrays;
import java.util.Scanner;

class Category {
    private int id;
    private String name;
    private String description;

    public Category() {
    }

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Tên danh mục: " + name);
        System.out.println("Mô tả: " + description);
        System.out.println("---------------------------");
    }
}

interface ICRUD {
    Category[] findAll();

    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteById(int id);
}

class CategoryManagement implements ICRUD {
    private Category[] categories = new Category[0];

    @Override
    public Category[] findAll() {
        return Arrays.copyOf(categories, categories.length);
    }

    @Override
    public void addCategory(Category category) {
        for (Category c : categories) {
            if (c.getId() == category.getId()) {
                System.out.println("ID danh mục đã tồn tại!");
                return;
            }
        }

        categories = Arrays.copyOf(categories, categories.length + 1);
        categories[categories.length - 1] = category;

        System.out.println("Thêm danh mục thành công!");
    }

    @Override
    public void updateCategory(Category category) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].getId() == category.getId()) {
                categories[i] = category;
                System.out.println("Cập nhật danh mục thành công!");
                return;
            }
        }

        System.out.println("Không tìm thấy danh mục!");
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].getId() == id) {
                for (int j = i; j < categories.length - 1; j++) {
                    categories[j] = categories[j + 1];
                }

                categories = Arrays.copyOf(categories, categories.length - 1);
                System.out.println("Xóa danh mục thành công!");
                return;
            }
        }

        System.out.println("Không tìm thấy danh mục!");
    }
}

public class bai5ss8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CategoryManagement management = new CategoryManagement();

        while (true) {
            System.out.println("\n========== QUẢN LÝ DANH MỤC ==========");
            System.out.println("1. Thêm danh mục mới");
            System.out.println("2. Hiển thị danh sách danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int luaChon;

            try {
                luaChon = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (luaChon) {
                case 1:
                    try {
                        System.out.print("Nhập ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("Nhập tên danh mục: ");
                        String name = scanner.nextLine();

                        System.out.print("Nhập mô tả: ");
                        String description = scanner.nextLine();

                        management.addCategory(new Category(id, name, description));
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ!");
                    }
                    break;

                case 2:
                    Category[] danhSach = management.findAll();

                    if (danhSach.length == 0) {
                        System.out.println("Danh sách danh mục đang trống!");
                    } else {
                        System.out.println("\n=== DANH SÁCH DANH MỤC ===");

                        for (Category category : danhSach) {
                            category.display();
                        }
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Nhập ID danh mục cần cập nhật: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());

                        boolean timThay = false;

                        for (Category category : management.findAll()) {
                            if (category.getId() == id) {
                                timThay = true;
                                break;
                            }
                        }

                        if (!timThay) {
                            System.out.println("Không tìm thấy danh mục!");
                            break;
                        }

                        System.out.print("Nhập tên mới: ");
                        String name = scanner.nextLine();

                        System.out.print("Nhập mô tả mới: ");
                        String description = scanner.nextLine();

                        management.updateCategory(new Category(id, name, description));
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ!");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Nhập ID danh mục cần xóa: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());

                        management.deleteById(id);
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ!");
                    }
                    break;

                case 5:
                    System.out.println("Kết thúc chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
