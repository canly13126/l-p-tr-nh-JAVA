
import java.util.Scanner;

public class bai5ss4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số hàng: ");
        int hang = scanner.nextInt();

        System.out.print("Nhập số cột: ");
        int cot = scanner.nextInt();

        if (hang <= 0 || cot <= 0) {
            System.out.println("Kích thước mảng không hợp lệ!");
            scanner.close();
            return;
        }

        int[][] arr = new int[hang][cot];
        long tongChan = 0;
        long tongLe = 0;

        System.out.println("Nhập các phần tử cho mảng (theo từng hàng):");

        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                System.out.print("arr[" + i + "][" + j + "] = ");
                arr[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                if (arr[i][j] % 2 == 0) {
                    tongChan += arr[i][j];
                } else {
                    tongLe += arr[i][j];
                }
            }
        }

        System.out.println("Tổng các số chẵn: " + tongChan);
        System.out.println("Tổng các số lẻ: " + tongLe);

        scanner.close();
    }
}
