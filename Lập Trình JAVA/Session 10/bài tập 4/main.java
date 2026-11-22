import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ ĐIỂM DANH =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị danh sách sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 5!");
                continue;
            }

            switch (choice) {
                case 1:
                    String addName;
                    while (true) {
                        System.out.print("Nhập tên sinh viên: ");
                        addName = scanner.nextLine().trim();
                        if (!addName.isEmpty()) {
                            break;
                        }
                        System.out.println("❌ Lỗi: Tên sinh viên không được để trống!");
                    }
                    manager.add(new Student(addName));
                    break;

                case 2:
                    if (manager.getSize() == 0) {
                        System.out.println("⚠️ Danh sách điểm danh hiện đang trống.");
                        break;
                    }
                    manager.display();
                    int updateIndex;
                    while (true) {
                        try {
                            System.out.print("Nhập số thứ tự sinh viên cần sửa (1 - " + manager.getSize() + "): ");
                            updateIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (updateIndex < 0 || updateIndex >= manager.getSize()) {
                                System.out.println("❌ Lỗi: Số thứ tự không tồn tại!");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                        }
                    }
                    String newName;
                    while (true) {
                        System.out.print("Nhập tên mới cho sinh viên: ");
                        newName = scanner.nextLine().trim();
                        if (!newName.isEmpty()) {
                            break;
                        }
                        System.out.println("❌ Lỗi: Tên sinh viên không được để trống!");
                    }
                    manager.update(updateIndex, new Student(newName));
                    break;

                case 3:
                    if (manager.getSize() == 0) {
                        System.out.println("⚠️ Danh sách điểm danh hiện đang trống.");
                        break;
                    }
                    manager.display();
                    int deleteIndex;
                    while (true) {
                        try {
                            System.out.print("Nhập số thứ tự sinh viên cần xóa (1 - " + manager.getSize() + "): ");
                            deleteIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (deleteIndex < 0 || deleteIndex >= manager.getSize()) {
                                System.out.println("❌ Lỗi: Số thứ tự không tồn tại!");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                        }
                    }
                    manager.delete(deleteIndex);
                    break;

                case 4:
                    manager.display();
                    break;

                case 5:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
}
