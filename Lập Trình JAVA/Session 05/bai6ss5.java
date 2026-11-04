
import java.util.Scanner;

public class bai6ss5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập mật khẩu: ");
        String matKhau = scanner.nextLine();

        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$!%]).{8,}$";

        if (matKhau.matches(regex)) {
            System.out.println("Mật khẩu hợp lệ");
        } else {
            System.out.println("Mật khẩu không hợp lệ");
        }

        scanner.close();
    }
}
