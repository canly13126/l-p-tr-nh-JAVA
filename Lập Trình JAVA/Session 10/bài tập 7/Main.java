import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ & PHÂN LOẠI SINH VIÊN =====");
            System.out.println("1. Nhập danh sách sinh viên (tối thiểu 5 sinh viên)");
            System.out.println("2. In danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo tên");
            System.out.println("4. Phân loại và hiển thị danh sách theo GPA");
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
                    inputStudents(scanner, studentList);
                    break;
                case 2:
                    displayStudents(studentList);
                    break;
                case 3:
                    searchStudentByName(scanner, studentList);
                    break;
                case 4:
                    displayClassifiedStudents(studentList);
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

    private static void inputStudents(Scanner scanner, ArrayList<Student> list) {
        int count;
        int minRequired = list.isEmpty() ? 5 : 1;

        while (true) {
            try {
                System.out.print("Nhập số lượng sinh viên muốn thêm" + (minRequired == 5 ? " (ít nhất 5): " : ": "));
                count = Integer.parseInt(scanner.nextLine());
                if (count < minRequired) {
                    System.out.println("❌ Lỗi: Vui lòng nhập ít nhất " + minRequired + " sinh viên!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Số lượng phải là một số nguyên hợp lệ!");
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Nhập thông tin sinh viên thứ " + (list.size() + 1) + " ---");

            int id;
            while (true) {
                try {
                    System.out.print("Nhập mã sinh viên: ");
                    id = Integer.parseInt(scanner.nextLine());
                    if (id <= 0) {
                        System.out.println("❌ Lỗi: Mã sinh viên phải là số nguyên dương!");
                        continue;
                    }
                    boolean isDuplicate = false;
                    for (Student s : list) {
                        if (s.getId() == id) {
                            isDuplicate = true;
                            break;
                        }
                    }
                    if (isDuplicate) {
                        System.out.println("❌ Lỗi: Mã sinh viên này đã tồn tại!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Lỗi: Mã sinh viên phải là một số nguyên!");
                }
            }

            String name;
            while (true) {
                System.out.print("Nhập họ và tên: ");
                name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    break;
                }
                System.out.println("❌ Lỗi: Họ và tên không được để trống!");
            }

            double gpa;
            while (true) {
                try {
                    System.out.print("Nhập điểm trung bình (GPA 0 - 10): ");
                    gpa = Double.parseDouble(scanner.nextLine());
                    if (gpa < 0 || gpa > 10) {
                        System.out.println("❌ Lỗi: GPA phải nằm trong khoảng từ 0.0 đến 10.0!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Lỗi: GPA phải là một số thực hợp lệ!");
                }
            }

            list.add(new Student(id, name, gpa));
        }
        System.out.println("✅ Đã thêm " + count + " sinh viên thành công!");
    }

    private static void displayStudents(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("⚠️ Danh sách sinh viên hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH TẤT CẢ SINH VIÊN ---");
        for (Student s : list) {
            System.out.println(String.format("Mã SV: %-6d | Họ tên: %-22s | GPA: %.2f",
                    s.getId(), s.getName(), s.getGpa()));
        }
    }

    private static void searchStudentByName(Scanner scanner, ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("⚠️ Danh sách sinh viên hiện đang trống.");
            return;
        }
        System.out.print("Nhập tên sinh viên cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
        for (Student s : list) {
            if (s.getName().toLowerCase().contains(keyword)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Không tìm thấy sinh viên nào có tên chứa \"" + keyword + "\".");
        }
    }

    private static void displayClassifiedStudents(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("⚠️ Danh sách sinh viên hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH SINH VIÊN SAU KHI PHÂN LOẠI ---");
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
