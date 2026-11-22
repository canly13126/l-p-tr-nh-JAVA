import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ DANH SÁCH SINH VIÊN =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng (1-3): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 3!");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent(scanner, studentList);
                    break;
                case 2:
                    displayStudents(studentList);
                    break;
                case 3:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void addStudent(Scanner scanner, ArrayList<Student> list) {
        String name;
        while (true) {
            System.out.print("Nhập tên sinh viên: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Tên sinh viên không được để trống!");
        }

        int age;
        while (true) {
            try {
                System.out.print("Nhập tuổi sinh viên: ");
                age = Integer.parseInt(scanner.nextLine());
                if (age <= 0) {
                    System.out.println("❌ Lỗi: Tuổi phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Tuổi phải là một số nguyên hợp lệ!");
            }
        }

        double gpa;
        while (true) {
            try {
                System.out.print("Nhập điểm trung bình: ");
                gpa = Double.parseDouble(scanner.nextLine());
                if (gpa < 0 || gpa > 10) {
                    System.out.println("❌ Lỗi: Điểm trung bình phải nằm trong khoảng 0 - 10!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Điểm trung bình phải là một số thực hợp lệ!");
            }
        }

        list.add(new Student(name, age, gpa));
        System.out.println("✅ Thêm sinh viên thành công!");
    }

    private static void displayStudents(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("⚠️ Danh sách sinh viên hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }
}
