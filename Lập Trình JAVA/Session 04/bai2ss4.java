
import java.util.Scanner;

public class bai2ss4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập kích thước mảng: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Kích thước mảng không hợp lệ!");
            scanner.close();
            return;
        }

        int[] arr = new int[n];
        long tong = 0;

        System.out.println("Nhập các phần tử cho mảng:");

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            tong += arr[i];
        }

        System.out.println("Tổng các phần tử trong mảng là: " + tong);

        scanner.close();
    }
}
