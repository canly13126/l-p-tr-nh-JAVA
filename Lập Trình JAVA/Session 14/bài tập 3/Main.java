import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        int age;
        while (true) {
            try {
                System.out.print("Nhập tuổi để xóa những học sinh có độ tuổi nhỏ hơn : ");
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

        studentManager.deleteStudentsByAge(age);

        scanner.close();
    }
}

