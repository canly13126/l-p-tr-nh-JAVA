
import java.util.Scanner;

public class bai3ss2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số nguyên dương N: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Số nhập vào không hợp lệ");
        } else {
            int tong = 0;

            for (int i = 1; i <= n; i++) {
                tong += i;
            }

            System.out.println("Tổng các số từ 1 đến " + n + " là: " + tong);
        }

        scanner.close();
    }
}
