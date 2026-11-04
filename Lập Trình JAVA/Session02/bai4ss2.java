
import java.util.Scanner;

public class bai4ss2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age;

        while (true) {
            System.out.print("Nhập tuổi của bạn: ");
            String input = scanner.nextLine().trim();

            try {
                age = Integer.parseInt(input);

                if (age > 0) {
                    break;
                }
            } catch (NumberFormatException e) {
            }

            System.out.println("Vui lòng nhập vào một số nguyên và lớn hơn 0");
        }

        System.out.println("Tuổi của bạn là " + age + "!");

        scanner.close();
    }
}
