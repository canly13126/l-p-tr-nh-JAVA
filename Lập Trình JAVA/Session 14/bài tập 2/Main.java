import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        int id;
        while (true) {
            try {
                System.out.print("Nhập ID sinh viên cần cập nhật: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("ID sinh viên không được để trống!");
                }
                id = Integer.parseInt(input);
                if (id <= 0) {
                    throw new IllegalArgumentException("ID sinh viên phải là số nguyên dương!");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID sinh viên phải là một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }

        String name;
        while (true) {
            try {
                System.out.print("Nhập tên mới cho sinh viên: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Tên sinh viên không được để trống!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }

        int age;
        while (true) {
            try {
                System.out.print("Nhập tuổi mới cho sinh viên: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Tuổi không được để trống!");
                }
                age = Integer.parseInt(input);
                if (age <= 0) {
                    throw new IllegalArgumentException("Tuổi phải lớn hơn 0!");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Tuổi phải là một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }

        Student updatedStudent = new Student(id, name, age);
        studentManager.updateStudent(updatedStudent);

        scanner.close();
    }
}
