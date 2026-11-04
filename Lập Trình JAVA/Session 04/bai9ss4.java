
import java.util.Scanner;

public class bai9ss4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số phần tử của mảng: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Mảng không hợp lệ");
            scanner.close();
            return;
        }

        int[] arr = new int[n];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int tongHienTai = arr[0];
        int tongLonNhat = arr[0];

        for (int i = 1; i < n; i++) {
            tongHienTai = Math.max(arr[i], tongHienTai + arr[i]);
            tongLonNhat = Math.max(tongLonNhat, tongHienTai);
        }

        System.out.println("Tổng lớn nhất của dãy con liên tiếp là: " + tongLonNhat);

        scanner.close();
    }
}
